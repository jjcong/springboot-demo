package com.jincong.springboot.rpc.config.spring.bean;

import com.alibaba.fastjson.JSON;
import com.jincong.springboot.rpc.config.ConsumerConfig;
import com.jincong.springboot.rpc.domain.domain.RpcProviderConfig;
import com.jincong.springboot.rpc.network.client.ClientSocket;
import com.jincong.springboot.rpc.network.msg.Request;
import com.jincong.springboot.rpc.reflect.JDKProxy;
import com.jincong.springboot.rpc.registry.RedisRegistryCenter;
import com.jincong.springboot.utils.ClassLoaderUtils;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.FactoryBean;


/**
 * 将XML中配置的消费接口，生成对应的Bean对象
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
public class ConsumerBean<T> extends ConsumerConfig implements FactoryBean {

    private ChannelFuture channelFuture;

    private RpcProviderConfig rpcProviderConfig;

    @Override
    public Object getObject() throws Exception {

        //从redis获取链接
        if (null == rpcProviderConfig) {
            String infoStr = RedisRegistryCenter.obtainProvider(nozzle, alias);
            rpcProviderConfig = JSON.parseObject(infoStr, RpcProviderConfig.class);
        }
        assert null != rpcProviderConfig;

        //获取通信channel
        if (null == channelFuture) {
            ClientSocket clientSocket = new ClientSocket(rpcProviderConfig.getHost(), rpcProviderConfig.getPort());
            new Thread(clientSocket).start();
            for (int i = 0; i < 100; i++) {
                if (null != channelFuture) {
                    break;
                }
                Thread.sleep(500);
                channelFuture = clientSocket.getFuture();
            }
        }
        
        Request request = new Request();
        request.setChannel(channelFuture.channel());
        request.setNozzle(nozzle);
        request.setRef(rpcProviderConfig.getRef());
        request.setAlias(alias);
        return (T) JDKProxy.getProxy(ClassLoaderUtils.forName(nozzle), request);
    }

    @Override
    public Class<?> getObjectType() {
        try {
            return ClassLoaderUtils.forName(nozzle);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}

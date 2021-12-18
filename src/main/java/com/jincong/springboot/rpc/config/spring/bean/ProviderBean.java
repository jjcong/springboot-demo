package com.jincong.springboot.rpc.config.spring.bean;

import com.alibaba.fastjson.JSON;
import com.jincong.springboot.rpc.config.ProviderConfig;
import com.jincong.springboot.rpc.domain.domain.LocalServerInfo;
import com.jincong.springboot.rpc.domain.domain.RpcProviderConfig;
import com.jincong.springboot.rpc.registry.RedisRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 将XML中配置的生产接口，生成对应的Bean对象
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ProviderBean.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        RpcProviderConfig rpcProviderConfig = new RpcProviderConfig();
        rpcProviderConfig.setNozzle(nozzle);
        rpcProviderConfig.setRef(ref);
        rpcProviderConfig.setAlias(alias);
        rpcProviderConfig.setHost(LocalServerInfo.LOCAL_HOST);
        rpcProviderConfig.setPort(LocalServerInfo.LOCAL_PORT);

        //注册生产者
        long count = RedisRegistryCenter.registryProvider(nozzle, alias, JSON.toJSONString(rpcProviderConfig));

        logger.info("注册生产者：{} {} {}", nozzle, alias, count);
    }

}

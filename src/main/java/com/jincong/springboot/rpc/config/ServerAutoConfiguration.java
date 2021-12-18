package com.jincong.springboot.rpc.config;

import com.jincong.springboot.rpc.domain.domain.LocalServerInfo;
import com.jincong.springboot.rpc.network.server.ServerSocket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 注册中心配置类
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
@Slf4j
public class ServerAutoConfiguration implements ApplicationContextAware {

    @Resource
    private ServerProperties serverProperties;


    @SneakyThrows
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("启动Redis模拟注册服务");

        log.info("启动Redis模拟注册中心结束，{} ， {}",serverProperties.getHost(), serverProperties.getPort());

        log.info("初始化生产端服务开始");

        ServerSocket serverSocket = new ServerSocket(applicationContext);

        Thread thread = new Thread(serverSocket);

        thread.start();

        while (!serverSocket.isActiveSocketServer()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("初始化生产端结束，{} {}", LocalServerInfo.LOCAL_HOST, LocalServerInfo.LOCAL_PORT);

    }
}

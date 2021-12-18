package com.jincong.springboot.rpc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rpc.server")
@Data
public class ServerProperties {

    /**
     * 注册中心地址
     */
    private String host;

    /**
     * 注册中心端口
     */
    private int port;

}

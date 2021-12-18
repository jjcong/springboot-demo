package com.jincong.springboot.rpc.annotation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/18
 */
@ConfigurationProperties("rpc.server")
@Data
public class EnableRpc {

    /**
     * 主机
     */
    private String host;

    /**
     * 端口号
     */
    private int port;
}

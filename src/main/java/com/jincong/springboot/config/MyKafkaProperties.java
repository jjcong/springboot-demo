package com.jincong.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MyKafkaProperties
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/20
 */
@ConfigurationProperties(prefix = "my.kafka")
@Data
public class MyKafkaProperties {

    private String broker;

    private String topic;

    private String groupId;

    private String client;

    private String enable;

}

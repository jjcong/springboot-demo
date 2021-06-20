package com.jincong.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MyKafkaProperties
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/20
 */
@ConfigurationProperties(prefix = "my.kafka")
public class MyKafkaProperties {

    private String broker;

    private String topic;

    private String groupId;

    private String client;

    private String enable;

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }





}

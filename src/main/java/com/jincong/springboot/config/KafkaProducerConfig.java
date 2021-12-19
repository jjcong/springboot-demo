package com.jincong.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/19
 */
@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic initTopic() {
        return new NewTopic("USER-TOPIC", 3, (short) 3);
    }
}

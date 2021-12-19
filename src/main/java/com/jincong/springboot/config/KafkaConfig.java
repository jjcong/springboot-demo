package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * KafkaConfig
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/20
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(MyKafkaProperties.class)
@Slf4j
public class KafkaConfig {

    @Autowired
    private KafkaConsumerListener kafkaConsumerListener;

    @Autowired
    private MyKafkaProperties myKafkaProperties;

    public static KafkaConsumer<String, String> kafkaConsumer;



    private static final AtomicBoolean isRunning = new AtomicBoolean(true);


    @Bean
    public void initConfig() {
        // kafka开关
        if ("N".equals(myKafkaProperties.getEnable())) {
            return;
        }

        Properties prop = new Properties();
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, myKafkaProperties.getBroker());
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, myKafkaProperties.getGroupId());
        prop.put(ConsumerConfig.CLIENT_ID_CONFIG, myKafkaProperties.getClient());

        kafkaConsumer= new KafkaConsumer<>(prop);
        kafkaConsumer.subscribe(Collections.singletonList(myKafkaProperties.getTopic()));
        KafkaListenJob kafkaListenJob = new KafkaListenJob(kafkaConsumerListener);

        Thread thread = new Thread(kafkaListenJob);
        thread.start();

    }
}

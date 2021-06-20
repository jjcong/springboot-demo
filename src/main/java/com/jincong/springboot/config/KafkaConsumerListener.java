package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

/**
 * KafkaConsumerListener
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/20
 */
@Slf4j
@Service
public class KafkaConsumerListener {

    void listen(ConsumerRecord<String, String> record) {
        log.info("KafkaConsumerListener 消费消息，topic={}, partition={}, offset={}, key={}, value={}",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }
}

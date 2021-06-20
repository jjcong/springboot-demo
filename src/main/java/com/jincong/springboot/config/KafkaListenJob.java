package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;

/**
 * KafkaListenJob
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/20
 */
@Slf4j
public class KafkaListenJob implements Runnable {

    private KafkaConsumerListener kafkaConsumerListener;

    public KafkaListenJob(KafkaConsumerListener listener) {
        this.kafkaConsumerListener = listener;
    }

    @Override
    public void run() {


        try {
            while (true) {
                ConsumerRecords<String, String> records = KafkaConfig.kafkaConsumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    kafkaConsumerListener.listen(record);


                }
            }
        } catch (Exception e) {
            log.error("Kafka Consumer Error", e);
        }
    }


}

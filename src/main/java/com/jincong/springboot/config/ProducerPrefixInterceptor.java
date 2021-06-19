package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * ProducerPrefixInterceptor
 * kafka生产者拦截器，实现在增加指定的前缀，并且统计发送消息的成功率
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/19
 */
@Slf4j
@Component
public class ProducerPrefixInterceptor implements ProducerInterceptor<String, String> {


    /**
     * 发送成功次数
     */
    private volatile long  sendSuccess  =  0;

    /**
     * 发送失败次数
     */
    private volatile long  sendFailure  =  0;


    /**
     * 优于消息序列化和计算分区执行
     * @param record
     * @return
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {

        String modifiedValue = "PREFIX-"+ record.value();

        return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(), record.key(), modifiedValue, record.headers());
    }


    /**
     * 消息在应答之前或消息发送失败时执行
     * @param metadata
     * @param exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

        if (Objects.isNull(exception)) {
            sendSuccess++;
        }

    }

    @Override
    public void close() {

        double successRatio = (double)sendSuccess / (sendSuccess + sendFailure);
        log.info("Kafka发送成功率 = {}", String.format ("%f" ,  successRatio *  100) + "%");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

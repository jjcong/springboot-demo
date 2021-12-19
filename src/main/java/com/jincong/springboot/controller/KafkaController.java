package com.jincong.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jincong.springboot.domain.MyTopic;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.result.BaseResult;
import com.jincong.springboot.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 测试Kafka
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/08/08
 */
@RestController
@Slf4j
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private IUserService userService;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @ApiOperation(value = "Kafka发送消息")
    @GetMapping("/sendMsg")
    public BaseResult sendMsg() {

        List<User> allUser = userService.findAllUser();

        allUser.forEach(user -> {
            ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send("USER-TOPIC", JSON.toJSONString(user));
            SendResult<String, String> stringStringSendResult;
            try {
                stringStringSendResult = sendResultListenableFuture.get();
                log.info("成功发送消息，Topic = 【{}】, Partition = 【{}】, offset = 【{}】",
                        stringStringSendResult.getRecordMetadata().topic(),
                        stringStringSendResult.getRecordMetadata().partition(),
                        stringStringSendResult.getRecordMetadata().offset());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        });




        return new BaseResult<>(allUser, "生产成功！！！");
    }

    @ApiOperation(value = "Kafka消费消息")
    @GetMapping("/consumeMsg")
    //@KafkaListener(topics = "TEST-TOPIC")
    public BaseResult consumeMsg(ConsumerRecord record) {

        log.info("消费者开始消费---------");
        log.info("参数为: {}", record);

        List list1 = JSONObject.parseObject(String.valueOf(record.value()), List.class);

        List<User> userList = JSON.parseArray(String.valueOf(record.value()), User.class);


        System.out.println(list1);

        log.info("消费数据： {}", list1);


        record.value();


        log.info("消费者消费结束---------");

        return new BaseResult();

    }

    @PostMapping("/createTopic")
    public BaseResult createTopic(@RequestBody MyTopic myTopic) {

        Properties prop = new Properties();
        prop.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        AdminClient adminClient = AdminClient.create(prop);

        NewTopic topic = new NewTopic(myTopic.getTopicName(), myTopic.getPartition(), (short) myTopic.getReplication());


        CreateTopicsResult topics = adminClient.createTopics(Collections.singletonList(topic));

        try {
            topics.all().get();
        } catch (InterruptedException  | ExecutionException e) {
            e.printStackTrace();
        }
        adminClient.close();
        return new BaseResult();
    }


    @PostMapping("/deleteTopic")
    public BaseResult deleteTopic(@ApiParam(value = "topicName") String topicName) {
        Properties prop = new Properties();
        prop.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        AdminClient adminClient = AdminClient.create(prop);

        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singletonList(topicName));

        try {
            deleteTopicsResult.all().get();
        } catch (InterruptedException  | ExecutionException e) {
            e.printStackTrace();
        }
        adminClient.close();
        return new BaseResult();
    }

}

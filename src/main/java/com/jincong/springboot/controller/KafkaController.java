package com.jincong.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.result.BaseResult;
import com.jincong.springboot.service.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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

    @ApiOperation(value = "Kafka发送消息")
    @GetMapping("/sendMsg")
    public BaseResult sendMsg() throws ExecutionException, InterruptedException {

        List<User> allUser = userService.findAllUser();

        ListenableFuture<SendResult<String, String>> sendResultListenableFuture = kafkaTemplate.send("TEST-TOPIC", JSON.toJSONString(allUser));

        SendResult<String, String> stringStringSendResult = sendResultListenableFuture.get();
        log.info("成功发送消息，Topic = 【{}】, Partition = 【{}】, offset = 【{}】",
                stringStringSendResult.getRecordMetadata().topic(),
                stringStringSendResult.getRecordMetadata().partition(),
                stringStringSendResult.getRecordMetadata().offset());


        return new BaseResult<>(allUser, "生产成功！！！");
    }


 /*   @ApiOperation(value = "Kafka消费消息")
    @GetMapping("/consumeMsg")
    @KafkaListener(topics = "TEST-TOPIC")
    public BaseResult consumeMsg(ConsumerRecord record) {

        log.info("消费者开始消费---------");
        log.info("参数为: {}", record);

        List list1 = JSONObject.parseObject(String.valueOf(record.value()), List.class);

        //List<User> userList = JSON.parseArray(String.valueOf(record.value()), User.class);



        System.out.println(list1);

        log.info("消费数据： {}", list1);


        record.value();


        log.info("消费者消费结束---------");

        return new BaseResult();

    }*/

}

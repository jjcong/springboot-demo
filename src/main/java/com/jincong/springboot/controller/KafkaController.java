package com.jincong.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    /*

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


    @ApiOperation(value = "Kafka消费消息")
    @GetMapping("/consumeMsg")
    @KafkaListener(topics = "TEST-TOPIC")
    public BaseResult consumeMsg(ConsumerRecord record) {

        log.info("消费者开始消费---------");

        //List list1 = JSONObject.parseObject(String.valueOf(record.value()), List.class);

        List<User> userList = JSON.parseArray(String.valueOf(record.value()), User.class);



        System.out.println(userList);

        log.info("消费数据： {}", userList);


        record.value();


        log.info("消费者消费结束---------");

        return new BaseResult();

    }
    */

}

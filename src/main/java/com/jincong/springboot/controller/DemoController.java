package com.jincong.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.HelloService;

/**
 * DemoController
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/1/9
 */
@RestController
@ResponseBody
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    HelloService helloService;


    @GetMapping("/hello")
    public String sayHello() {
        return helloService.sayHello("Hello");

    }

}

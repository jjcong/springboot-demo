package com.jincong.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/1/9
 */
@RestController
@ResponseBody
public class DemoController {


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";

    }

}

package com.jincong.springboot.controller;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    IUserService userService;

    @RequestMapping("/findAllUser")
    public List<User> findAllUser() {
        System.out.println("I am  a teacher!");
        return userService.findAllUser();
    }
}

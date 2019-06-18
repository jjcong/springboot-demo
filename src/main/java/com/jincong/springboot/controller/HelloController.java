package com.jincong.springboot.controller;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    IUserService userService;

    @RequestMapping("/findAllUser")
    public List<User> findAllUser() {

        return userService.findAllUser();
    }

    @RequestMapping("/findUserByUserName")
    public List<User> findAllUser(@RequestParam Map<String, String> request) {

        return userService.findUserByUserName(request);
    }

    @RequestMapping("/addUser")
    public boolean addUser(@RequestParam Map<String, String>  request) {

        User newUser = new User();
        newUser.setUserId(Integer.valueOf(request.get("userId")));
        newUser.setUserName(request.get("userName"));
        newUser.setPassword(request.get("password").replaceAll("-", ""));
        Date curDate = new Date();
        newUser.setCreateTime(curDate);

        int result = userService.addUser(newUser);

        return result > 0;
    }


}

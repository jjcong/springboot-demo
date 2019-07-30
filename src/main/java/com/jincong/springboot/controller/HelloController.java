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
/**
 * 接口Controller
 *
 * @author  j_cong
 * @date    2019/07/28
 * @version V1.0
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    IUserService userService;

    @RequestMapping("/findAllUser")
    public List<User> findAllUser() {

        return userService.findAllUser();
    }

    @RequestMapping(value = "/findUserByUserName")
    public List<User> findAllUser(@RequestParam Map<String, String> request) {

        return userService.findUserByUserName(request);
    }

    @RequestMapping("/addUser")
    public boolean addUser(@RequestParam Map<String, String>  request) {
        User newUser = new User();
        newUser.setUserId(Integer.parseInt(request.get("userId")));
        newUser.setUserName(request.get("userName").trim());
        newUser.setPassword(request.get("password").replaceAll("-", ""));
        newUser.setCreateTime(new Date());

        int result = userService.addUser(newUser);

        return result > 0;
    }

    @RequestMapping(value = "/delBatchUser")
    public boolean delBatchUser(@RequestParam String  ids) {

        String[] idList = ids.split(",");
        int[] arr = new int[idList.length];
        for (int i = 0; i < idList.length; i++) {
            arr[i] = Integer.parseInt(idList[i]);
        }
        int result = userService.delBatchUser(arr);

        return result > 0;
    }


}

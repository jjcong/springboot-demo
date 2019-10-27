package com.jincong.springboot.controller;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.service.IUserService;
import com.jincong.springboot.service.RedisTemplateService;
import com.jincong.springboot.vo.QueryUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplateService redisTemplateService;

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    public List<User> findAllUser() {
        List<User> userList = userService.findAllUser();
        //查询指定姓名的用户
        List<User> resultList = userList.stream().filter(user -> "354张无忌".equalsIgnoreCase(user.getUserName()))
                .collect(Collectors.toList());

        //获取所有用户的姓名
        List<String> userNameList = userList.stream().map(User::getUserName).distinct().collect(Collectors.toList());
        System.out.println(userNameList);
        return userList;
    }
    @RequestMapping(value = "/findUserByUserName", method = RequestMethod.POST )
    public List<User> findUserByUserName(@RequestParam String userName) {

        if (userName == null) {
            return null;
        }

        return userService.findUserByUserName(userName);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody QueryUserVO userVO) {

        User newUser = new User();
        newUser.setUserName(userVO.getUserName());
        newUser.setPassword(userVO.getPassword());
        newUser.setRemark(userVO.getRemark());
        int result = userService.addUser(newUser);

        return result > 0;
    }

    @PostMapping(value = "/delBatchUser")
    public boolean delBatchUser(@RequestParam String  ids) {
        if (ids == null || "".equalsIgnoreCase(ids)) {
            return false;
        }

        String[] idList = ids.split(",");
        int[] arr = new int[idList.length];
        for (int i = 0; i < idList.length; i++) {
            arr[i] = Integer.parseInt(idList[i]);
        }
        int result = userService.delBatchUser(arr);

        return result > 0;
    }


    @PostMapping("/updateUser")
    public boolean updateUser(@RequestBody User user) {

        String userName = user.getUserName();
        List<User> userList = userService.findUserByUserName(userName);
        int result = userService.updateUser(userList.get(0));

        return result > 0;
    }

    @RequestMapping("/redisTest")
    public void testRedis() {
        User user = new User();
        user.setId(1);
        user.setUserName("张无忌");
        user.setPassword("12345678");
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());
        user.setRemark("测试Redis格式化");

        redisTemplateService.set("redis:user:1", user);

        StopWatch stopWatch = new StopWatch("Redis");
        stopWatch.start("testRedis");
        User us = redisTemplateService.get("redis_user_1", User.class);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(us);

    }

}

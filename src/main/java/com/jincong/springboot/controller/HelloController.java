package com.jincong.springboot.controller;

import com.jincong.springboot.VO.QueryUserVO;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    public List<User> findAllUser() {
        return userService.findAllUser();
    }
    @PostMapping(value = "/findUserByUserName" )
    public List<User> findAllUser(@RequestBody QueryUserVO queryUserVO) {
        String userName = null;

        if (queryUserVO.getUserName() != null) {
            userName = queryUserVO.getUserName();
        }

        return userService.findUserByUserName(userName);
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


}

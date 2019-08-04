package com.jincong.springboot.service;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;

@Service("userService")
public class IUserServiceImpl implements IUserService {

   @Autowired
   private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {

        StopWatch stopWatch = new StopWatch("查询用户计时统计");
        stopWatch.start("查询用户");

        List<User> res = userMapper.findAllUser();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        return res;

    }

    @Override
    public List<User> findUserByUserName(Map map) {
        return userMapper.findUserByUserName(map);
    }

    @Override
    public int addUser(User user) {

        return userMapper.addUser(user);
    }

    @Override
    public int delBatchUser(int[] ids) {
        return userMapper.delBatchUser(ids);
    }
}

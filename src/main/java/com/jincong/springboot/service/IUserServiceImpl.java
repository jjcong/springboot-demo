package com.jincong.springboot.service;

import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class IUserServiceImpl implements IUserService {

   @Autowired
   private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {

        return userMapper.findAllUser();

    }

    @Override
    public List<User> findUserByUserName(Map map) {
        return userMapper.findUserByUserName(map);
    }

    @Override
    public int addUser(User user) {

        int index = userMapper.addUser(user);
        return index;
    }
}

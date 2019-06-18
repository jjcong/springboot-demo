package com.jincong.springboot.service;

import com.jincong.springboot.domain.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<User> findAllUser();

    List<User> findUserByUserName(Map map);

    int addUser(User user);
}

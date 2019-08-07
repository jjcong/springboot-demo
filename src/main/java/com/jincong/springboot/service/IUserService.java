package com.jincong.springboot.service;

import com.jincong.springboot.domain.User;

import java.util.List;

public interface IUserService {

    List<User> findAllUser();

    List<User> findUserByUserName(String userName);

    int addUser(User user);

    int delBatchUser(int[] ids);
}

package com.jincong.springboot.service;

import com.jincong.springboot.domain.User;

import java.util.List;

/**
 * @author j_cong
 */
public interface IUserService {

    List<User> findAllUser();

    List<User> finduserbyuserName(String userName);

    int addUser(User user);

    int delBatchUser(int[] ids);

    int updateUser(User user);
}

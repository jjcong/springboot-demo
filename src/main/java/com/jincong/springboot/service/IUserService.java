package com.jincong.springboot.service;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.vo.UserVO;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author j_cong
 */
public interface IUserService {

    List<User> findAllUser();

    List<User> findUserByUserName(String userName);

    UserVO findUserByUserCode(String userCode);

    User findUserById(int id);

    int addUser(User user);

    int delBatchUser(int[] ids);

    int updateUser(User user);

    List<User> listUserByCondition(User user);

    Future<String> jobOne() throws InterruptedException;

    Future<String> jobTwo() throws InterruptedException;

    Future<String> jobThree() throws InterruptedException;

    User getUserByListener();


}

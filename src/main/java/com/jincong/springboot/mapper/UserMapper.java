package com.jincong.springboot.mapper;

import com.jincong.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

   List<User> findAllUser();

   List<User> findUserByUserName(Map request);

   int addUser(User user);

   int delBatchUser(int[] ids);

}

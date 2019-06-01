package com.jincong.springboot.mapper;

import com.jincong.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

   List<User> findAllUser();

}

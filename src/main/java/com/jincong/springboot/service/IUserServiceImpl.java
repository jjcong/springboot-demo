package com.jincong.springboot.service;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.mapper.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("userService")
public class IUserServiceImpl implements IUserService {

   @Autowired
   private UserMapper userMapper;

   @Autowired
   private UserMapper1 newUserMapper;

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
    public List<User> findUserByUserName(String userName) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(userName);

        return newUserMapper.selectByExample(example);
//        return userMapper.findUserByUserName(map);
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

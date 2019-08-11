package com.jincong.springboot.service;

import cn.hutool.core.util.RandomUtil;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.mapper.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Random;

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
        User user = new User();

        List<User> res = newUserMapper.select(user);

//        List<User> res = userMapper.findAllUser();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        return res;

    }

    @Override
    public List<User> findUserByUserName(String userName) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userName","%" + userName + "%");

        return newUserMapper.selectByExample(example);
    }

    @Override
    public int addUser(User user1) {
        User user = new User();

        Random ra =new Random();
        user.setUserId(ra.nextInt(10) + 1);
        user.setUserName(RandomUtil.randomString(6));
        user.setPassword(RandomUtil.randomString(8));
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());

        return newUserMapper.insert(user);

    }

    @Override
    public int delBatchUser(int[] ids) {

        return userMapper.delBatchUser(ids);
    }

    @Override
    public int updateUser(User user) {

        user.setUserName("宋大宝");
        user.setLastUpdateTime(new Date());

        return newUserMapper.updateByPrimaryKeySelective(user);
    }
}

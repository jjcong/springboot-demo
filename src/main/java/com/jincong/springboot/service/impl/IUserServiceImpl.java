package com.jincong.springboot.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.jincong.springboot.config.SexEnum;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.handler.MyEvent;
import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.service.IUserService;
import com.jincong.springboot.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@Service("userService1")
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private com.jincong.springboot.mapper.newUserMapper newUserMapper;

    @Resource
    private ApplicationContext applicationContext;


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

        criteria.andLike("userName", "%" + userName + "%");

        return newUserMapper.selectByExample(example);
    }

    @Override
    public UserVO findUserByUserCode(String userCode) {

        return userMapper.findUserByUserCode(userCode);
    }

    @Override
    public User findUserById(int id) {
        return newUserMapper.selectByPrimaryKey(id);

    }

    @Override
    public int addUser(User user1) {



        User user = new User();
        StopWatch stopWatch = new StopWatch("测试插入10000条数据");
        stopWatch.start("任务开始");

        user.setUserCode("bigger1993");
        user.setUserName("型男");
        user.setPassword(RandomUtil.randomString(8));
        user.setSex(SexEnum.MAN);
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());


        return newUserMapper.insert(user);

    }

    @Override
    public int delBatchUser(int[] ids) {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("id", 1);
        User user = new User();
        user.setUserName("段天涯");

        newUserMapper.updateByExample(user, example);

        criteria.andIn("id", Collections.singletonList(ids));

        return userMapper.delBatchUser(ids);
    }

    @Override
    public int updateUser(User user) {
        User user1 = new User();
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("id", 5);
        user1.setUserName("段天涯");
        newUserMapper.updateByExampleSelective(user1, example);


        return newUserMapper.updateByPrimaryKeySelective(user1);
    }

    @Override
    public List<User> listUserByCondition(User user) {
        if (user == null) {
            return Collections.emptyList();
        }
        Weekend<User> userWeekend = new Weekend<>(User.class);
        WeekendCriteria<User, Object> weekendCriteria = userWeekend.weekendCriteria();
        weekendCriteria.orLike(User::getUserName, user.getUserName())
                .orLike(User::getPassword, user.getPassword());

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("remark", user.getRemark());
        userWeekend.and(criteria);

        return newUserMapper.selectByExample(userWeekend);

    }

    @Override
    @Async
    public Future<String> jobOne() throws InterruptedException {

        System.out.println("开始执行任务一");
        long l1 = System.currentTimeMillis();
        Thread.sleep(2000);
        long l2 = System.currentTimeMillis();
        System.out.println("任务一用时" + (l2 - l1) + " ms");

        return new AsyncResult<>("任务一完成");
    }

    @Override
    @Async
    public Future<String> jobTwo() throws InterruptedException {
        System.out.println("开始执行任务二");
        long l1 = System.currentTimeMillis();
        Thread.sleep(2000);
        long l2 = System.currentTimeMillis();
        System.out.println("任务二用时" + (l2 - l1) + " ms");

        return new AsyncResult<>("任务二完成");
    }

    @Override
    @Async
    public Future<String> jobThree() throws InterruptedException {
        System.out.println("开始执行任务三");
        long l1 = System.currentTimeMillis();
        Thread.sleep(2000);
        long l2 = System.currentTimeMillis();
        System.out.println("任务三用时" + (l2 - l1) + " ms");
        return new AsyncResult<>("任务三完成");
    }

    /**
     * 手动触发事件
     *
     * @return
     */
    @Override
    public User getUserByListener() {
        //User user = findUserById(7);
        User user = new User();
        MyEvent event = new MyEvent(this, user);
        applicationContext.publishEvent(event);
        return user;
    }
}

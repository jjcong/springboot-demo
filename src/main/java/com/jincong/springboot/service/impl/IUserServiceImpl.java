package com.jincong.springboot.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.handler.MyEvent;
import com.jincong.springboot.mapper.NewUserMapper;
import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.service.IUserService;
import com.jincong.springboot.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.BeanUtils;
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
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service("userService1")
@Slf4j
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewUserMapper newUserMapper;

    @Resource
    private ApplicationContext applicationContext;
    private String remark;


    /**
     *  PostConstruct注解使该方法在程序启动时自动运行
     * @return
     */
    //@PostConstruct
    @Override
    public List<User> findAllUser() {


        List<Integer> result2 = userMapper.existData();

        CatVO catVO = new CatVO();
        catVO.setColor("红");
        catVO.setPrice("100");
        catVO.setCount("12");
        CatVO catVO1 = new CatVO();
        catVO1.setColor("黄");
        catVO1.setPrice("200");
        catVO1.setCount("22");

        List<CatVO> catVOS = new ArrayList<>();
        catVOS.add(catVO);
        catVOS.add(catVO1);

        HouseVO houseVO = new HouseVO();
        houseVO.setName("HouseVo");
        houseVO.setAge(18);
        houseVO.setCats(catVOS);

        List<HouseVO> houseVOList = new ArrayList<>();
        houseVOList.add(houseVO);
        HouseDTO houseDTO = new HouseDTO();

        BeanUtils.copyProperties(houseVO, houseDTO);

        HouseDTO dto = JSON.parseObject(JSON.toJSONString(houseVO), HouseDTO.class);

        String hounseStr = JSON.toJSONString(houseVOList);
        List<HouseDTO> houseDTOList = JSON.parseArray(hounseStr, HouseDTO.class);
        List<CatDTO> catDTO = houseDTOList.get(0).getCats();


        List<String> list1 = Arrays.asList("语文", "数学", "英语", "物理");
        List<String> list2 = Arrays.asList("苹果", "香蕉", "菠萝", "橘子");
        List<String> list3 = Arrays.asList("游泳", "散步", "骑行", "登高");

        List<List<String>> stringList = new ArrayList<>();
        stringList.add(list1);
        stringList.add(list2);
        stringList.add(list3);


        List<String> result = stringList.stream().flatMap(Collection::stream).collect(Collectors.toList());



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

        List<Integer> userIds = Arrays.asList(1, 2, 4);

        //List<User> result = userMapper.findUserByUserNameAndIds(userName, userIds);

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("userName", "%" + userName + "%");

        return newUserMapper.selectByExample(example);
    }

    @Override
    public List<User> findUserByUserNameAndIds(@NotNull String userName, @NotNull List<Integer> userIds) {
        return userMapper.findUserByUserNameAndIds(userName, userIds);
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


        //User user = new User();
        //StopWatch stopWatch = new StopWatch("测试插入10000条数据");
        //stopWatch.start("任务开始");
        //
        //user.setUserCode("bigger1993");
        //user.setUserName("型男");
        //user.setPassword(RandomUtil.randomString(8));
        //user.setSex(SexEnum.MAN);
        //user.setCreateTime(new Date());
        //user.setLastUpdateTime(new Date());

        return userMapper.addUser(user1);


        //return newUserMapper.insert(user1);

    }

    @Override
    public int batchInsert(List<User> userList) {

        if (CollectionUtil.isEmpty(userList)) {
            return -1;
        }
        return userMapper.batchInsertUser(userList);
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
        userWeekend.weekendCriteria().andEqualTo(User::getUserName, "1").andEqualTo(User::getPassword, "2");
        WeekendCriteria<User, Object> weekendCriteria = userWeekend.weekendCriteria();
        weekendCriteria.orLike(User::getUserName, user.getUserName())
                .orLike(User::getPassword, user.getPassword());

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        remark = "remark";
        criteria.andEqualTo(remark, user.getRemark());
        userWeekend.and(criteria);

        return newUserMapper.selectByExample(userWeekend);

    }

    @Override
    @Async
    public Future<String> jobOne() throws InterruptedException {

        System.out.println("开始执行任务一11");
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

    @Override
    public boolean existData() {
        return false;
    }

    @Override
    public Object[] webService(String param) {


        String methodName = "findUserByUserName";
        String wsdlUrl = "http://localhost:8080/IUserService?wsdl";
        //动态调用的客户端工厂类
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        final Client client = factory.createClient(wsdlUrl);
        Object[] result = null;
        try {
            result = client.invoke(methodName, param);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("WebService Result: {}", result);
        return result;
    }


}

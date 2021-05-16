package com.jincong.springboot;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.mapper.NewUserMapper;
import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class MockTest {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewUserMapper newUserMapper;


    @Test
    public void testHello() {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("userCode", "");
        criteria.andIsNull("userCode");

        List<User> userList = newUserMapper.selectByExample(example);


        //List<User> userList = userMapper.findAllUser();
        System.out.println(userList);


    }

    @Test
    public void testSplit() {

     List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<List<Integer>> result1 = ListUtil.groupBySize(list, 10);

        List<List<Integer>> result2 = ListUtil.averageList(list, 4);

        System.out.println(result2);
    }

    /**
     * 测试List的应用
     */

    @Test
    public void generateDocumentByTable() {

        List<Integer> tempList = new ArrayList<>();
        List<Integer> listB = Arrays.asList(1);
        List<Integer> listC = Arrays.asList(1,2);
        List<Integer> listD = Arrays.asList(1,2,3);

        List<List<Integer>> list = new ArrayList<>();
        list.add(listB);
        list.add(listC);
        list.add(listD);

        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> item : list) {
            tempList.clear();
            tempList.addAll(item);
            result.add(tempList);
        }

        System.out.println("result = " + result);

    }

    @Test
    public void testJoiner() {

        List<Integer> tempList = new ArrayList<>();
        List<Integer> listB = Arrays.asList(1);
        List<Integer> listC = Arrays.asList(1,2);
        List<Integer> listD = Arrays.asList(1,2,3);


        List<String> strList = Arrays.asList("A", "B", "C");


        log.info("strList={}", String.join(",", strList));


    }


    @Test
    public void testOptional() {
        User user = null;

        String s = Optional.ofNullable(user)
                .map(User::getUserName)
                .orElse("DEFAULT");

        Optional.ofNullable(user).ifPresent(User::getUserName);


        User user1 = Optional.ofNullable(user).filter(u -> Objects.equals("Alice", u.getUserName()))
                .orElseGet(() -> {
                    User temp = new User();
                    temp.setUserName("Alice");
                    return temp;
                });
        log.info("result={}", s);
    }


}

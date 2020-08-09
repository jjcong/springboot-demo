package com.jincong.springboot.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.pojo.OrderDTO;
import com.jincong.springboot.pojo.TOrder;
import com.jincong.springboot.result.BaseResult;
import com.jincong.springboot.service.*;
import com.jincong.springboot.vo.QueryUserVO;
import com.jincong.springboot.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 接口Controller
 *
 * @author j_cong
 * @version V1.0
 * @date 2019/07/28
 */

// RestController 注解相当于@Controller和@ResponseBody两个注解
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    RedisTemplateService redisTemplateService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    JobService jobService;

    @Autowired
    AccoutService accoutService;

    @Autowired
    CommonService commonService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    IOrderService orderService;


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "全量获取用户列表")
    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
    public BaseResult findAllUser() {

        List<User> userList = userService.findAllUser();
//        userList = new ArrayList<>();
        Map<String, User> maps = userList.stream()
                .collect(Collectors.toMap(User::getUserCode, Function.identity(), (o1, o2) -> o2));
        Map<Integer, User> maps1 = userList.stream()
                .collect(Collectors.toMap(User::getId, Function.identity(), (o1, o2) -> o1, TreeMap::new));


//        Map<String, List<User>> userMap = userList.stream().collect(Collectors.groupingBy(User::getSex));
        //查询指定姓名的用户
        List<User> resultList = userList.stream().filter(user -> "786张无忌".equalsIgnoreCase(user.getUserName()))
                .collect(Collectors.toList());

        //获取所有用户的姓名
        List<String> userNameList = userList.stream().map(User::getUserName).distinct().collect(Collectors.toList());
        Date minDate = userList.stream().min(Comparator.comparing(User::getCreateTime)).orElse(null).getCreateTime();
        System.out.println(userNameList);

        return new BaseResult<>(userList, "获取用户信息成功");
    }

    @ApiOperation(value = "根据用户名称获取用户列表")
    @GetMapping(value = "/findUserByUserName")
    public List<User> findUserByUserName(@RequestParam @ApiParam(value = "用户名称") String userName) {
        return userService.findUserByUserName(userName);
    }



    @ApiOperation(value = "根据用户名编号获取用户列表")
    @GetMapping(value = "/findUserByUserCode")
    public UserVO findUserByUserCode(@RequestParam @ApiParam(value = "用户编码") String userCode) {

        //UserVO userVO1 = userMapper.annotationFindUserByUserCode(userCode);
        //System.out.println(userVO1);
        return userService.findUserByUserCode(userCode);
    }

    @ApiOperation(value = "根据用户Id称获取用户信息")
    @GetMapping("/findUser/{code}")
    public BaseResult findUserById(@PathVariable(value = "code") @ApiParam(value = "用户id") Integer id) {
        //匹配/findUser/{id]的restful风格 url
        //如果希望自动匹配，则必须保证url中的参数名称与Controller方法中的参数保持一致，
        //否则，需要手动设置value保证参数一致
        return new BaseResult<>(userService.findUserById(id));
    }

    @GetMapping("/findUser")
    public BaseResult findUserById1(@RequestParam Integer id) {
        // 匹配/findUser？id=1 风格url，通常用于表单提交
        //如果希望自动匹配，则必须保证url中的参数名称与Controller方法中的参数保持一致，
        //否则，需要手动设置value保证参数一致
        return new BaseResult<>(userService.findUserById(id));
    }


    @PostMapping("/addUser")
    public boolean addUser(@RequestBody QueryUserVO userVO) {

        User newUser = new User();
        newUser.setUserName(userVO.getUserName());
        newUser.setPassword(userVO.getPassword());
        newUser.setRemark(userVO.getRemark());
        newUser.setCreateTime(new Date());
        newUser.setLastUpdateTime(new Date());

        return userService.addUser(newUser) > 0;
    }

    @PostMapping("/listUserByCondition")
    public List<User> listUserByCondition(@RequestBody User userVO) {
        return userService.listUserByCondition(userVO);
    }

    @GetMapping(value = "/delBatchUser")
    public boolean delBatchUser(@RequestParam String ids) {
        if (StringUtils.isEmpty(ids)) {
            return false;
        }
        String[] idArr = StringUtils.split(ids, ",");
        int[] arr = Arrays.stream(idArr).mapToInt(Integer::valueOf).toArray();

        int result = userService.delBatchUser(arr);

        return result > 0;
    }


    @PostMapping("/updateUser")
    public boolean updateUser(@RequestBody User user) {

        String userName = user.getUserName();
        List<User> userList = userService.findUserByUserName(userName);
        int result = userService.updateUser(userList.get(0));

        return result > 0;
    }

    @RequestMapping("/redisTest")
    public void testRedis() {
        User user = new User();
        user.setId(3);
        user.setUserName("张无忌");
        user.setPassword("12345678");
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());
        user.setRemark("测试Redis格式化");

        redisTemplateService.set("redis:user:1", user);

        StopWatch stopWatch = new StopWatch("Redis");
        stopWatch.start("testRedis");
        User us = redisTemplateService.get("redis:user:1", User.class);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(us);

    }


    @PostMapping("/asyncJobTest")
    public void asyncJobTest() {

        try {
            long l1 = System.currentTimeMillis();
            System.out.println("请求访问异步开始");
            Future<String> oneResult = userService.jobOne();
            Future<String> twoResult = userService.jobTwo();
            Future<String> threeResult = userService.jobThree();

            while (true) {
                if (oneResult.isDone() && twoResult.isDone() && threeResult.isDone()) {
                    break;
                }
                Thread.sleep(2000);
            }

            long l2 = System.currentTimeMillis();
            System.out.println("方法一返回结果： " + oneResult.get());
            System.out.println("方法二返回结果： " + twoResult.get());
            System.out.println("方法三返回结果： " + threeResult.get());
            System.out.println("请求访问异步结束，耗时" + (l2 - l1) + " ms");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/testJob")
    public void testJon() {
        jobService.timerToNow();
    }


    @PostMapping("/testException")
    public BaseResult testException(@RequestParam("name") String name, @RequestParam("password") String password) {

        LOGGER.info("name, {}", name);
        LOGGER.info("password, {}", password);

        return new BaseResult<>();
    }

    @PostMapping("/testListener")
    public User testListener(HttpServletRequest request) {

        ServletContext application = request.getServletContext();

        return (User) application.getAttribute("user");
    }

    @GetMapping("/publish")
    public User publishEvent() {
        return userService.getUserByListener();
    }



    @RequestMapping("/testTransaction")

    public BaseResult testTransaction() throws Exception {

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(10*1000);
        httpRequestFactory.setConnectTimeout(10*1000);
        httpRequestFactory.setReadTimeout(10*1000);



/*
        Class accoutServiceClass = Class.forName("com.jincong.springboot.service.impl.AccoutServiceImpl");

        AccoutServiceImpl reflectServcie = (AccoutServiceImpl) accoutServiceClass.newInstance();*/


        return new BaseResult<>(accoutService.transferMoney());
    }


    /**
     * 导出百万数据性能测试
     * @return
     */
    @GetMapping("/export")
    public BaseResult export(@RequestParam(name = "startTime")  DateTime startTime,
                             @RequestParam(name = "endTime")  DateTime endTime,  HttpServletResponse response) throws Exception{


        long start = System.currentTimeMillis();
        String fileName = URLEncoder.encode(String.format("%s-(%s).xlsx", "订单支付数据", UUID.randomUUID().toString()),
                StandardCharsets.UTF_8.toString());
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        ExcelWriter writer = new ExcelWriterBuilder()
                .autoCloseStream(true)
                .excelType(ExcelTypeEnum.XLS)
                .file(response.getOutputStream())
                .build();


        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName("target");

        long lastBatchMaxId = 0L;
        int limit = 500;

        for(;;) {
            List<OrderDTO> orderList = orderService.listOrdersByScrollingPagination(lastBatchMaxId, limit, startTime, endTime);
            if (CollectionUtil.isEmpty(orderList)) {
                break;
            } else {
                lastBatchMaxId = orderList.stream().map(OrderDTO::getId).max(Long::compareTo).orElse(Long.MAX_VALUE);
                writer.write(orderList, writeSheet);
            }

        }
        log.info("导出数据耗时:{} ms,start:{},end:{}", System.currentTimeMillis() - start, startTime, endTime);


        return new BaseResult(true);
    }


    @PostMapping("/addOrder")
    public boolean addOrder(@RequestBody QueryUserVO userVO) {

        long start = System.currentTimeMillis();
        int count = 5000000;

        for (int i = 0; i < count; i++) {
            TOrder order = new TOrder();
            order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
            order.setAmount(new BigDecimal(new Random().nextInt(1000)));
            order.setPaymentTime(new Date());
            order.setOrderStatus((byte) ((byte) i % 3));
            orderService.insertOrder(order);
        }

        log.info("插入耗时:{} ms,start:{},end:{}", System.currentTimeMillis() - start, start);


        return true;
    }
}

package com.jincong.springboot.handler;

import com.jincong.springboot.domain.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器，监听MyEvent事件
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/04/06
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        //    获取监听的数据
        User user = event.getUser();

        System.out.println("用户名： " + user.getUserName());
        System.out.println("用户密码： " + user.getPassword());
    }
}

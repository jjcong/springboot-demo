package com.jincong.springboot.handler;

import com.jincong.springboot.domain.User;
import com.jincong.springboot.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * 自定义方法监听器,重写onApplicationEvent
 * 
 * @author  j_cong
 * @date    2020/04/05
 * @version V1.0
 */
@Component
public class MyServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 获取Application上下文
        ApplicationContext applicationContext = event.getApplicationContext();
        // 获取对应的service
        IUserService userService = applicationContext.getBean(IUserService.class);
        User user = userService.findUserById(7);
        //将获取的用户信息存入application域中
        ServletContext application = applicationContext.getBean(ServletContext.class);
        application.setAttribute("user", user);
    }
}

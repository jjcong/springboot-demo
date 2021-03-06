package com.jincong.springboot.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 自动配置类测试
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/01/17
 */
@Configuration
public class CurrentDateConfigBean {
    //    相当于<bean id="currentDate" class="java.util.Date"/>
    @Bean
    public Date currentDog() {
        return new Date();
    }
}

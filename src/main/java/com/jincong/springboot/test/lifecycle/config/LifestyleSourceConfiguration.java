package com.jincong.springboot.test.lifecycle.config;

import com.jincong.springboot.test.lifecycle.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Peron的自动注入配置
 *
 * @author  j_cong
 * @date    2020/11/18
 * @version V1.0
 */

@Configuration
@ComponentScan("com.jincong.springboot.test.lifecycle.config")
public class LifestyleSourceConfiguration {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Person person() {
        Person person = new Person();
        person.setName("张无忌");
        return person;
    }
}

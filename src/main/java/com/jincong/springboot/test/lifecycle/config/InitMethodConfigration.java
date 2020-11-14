package com.jincong.springboot.test.lifecycle.config;


import com.jincong.springboot.test.lifecycle.bean.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * 自动配置类，增加初始化以及销毁方法
 *
 * @author  j_cong
 * @date    2020/11/07
 * @version V1.0
 */
@Configuration
@ComponentScan("com.jincong.springboot.test.lifecycle.config")
public class InitMethodConfigration {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Dog dog() {
        Dog dog = new Dog();
        dog.setName("黑豆");
        return dog;
    }
}

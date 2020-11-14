package com.jincong.springboot.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com.jincong.springboot.test")
public class DogConfigrationBean {
    @Bean()
    public Dog dog1() {
        return new Dog("My dog");
    }
}

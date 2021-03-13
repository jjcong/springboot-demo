package com.jincong.springboot.test.lifecycle.bean;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试对象的生命周期专用对象
 *
 * @author  j_cong
 * @date    2020/11/07
 * @version V1.0
 */
@Slf4j
public class Dog {

    private String name;


    public Dog() {
        //log.info("Dog 构造方法执行了。。。");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        //log.info("setName方法执行了。。。");
        this.name = name;
    }

    public void init() {
        //log.info("{} 被初始化了。。。。。。", name);
    }

    public void destroy() {
        //log.info("{} 被销毁了。。。。。。", name);
    }
}

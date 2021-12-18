package com.jincong.springboot.service;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/14
 */
public abstract class Parent {

    protected abstract String hello();

    public abstract String getType();

    public void say() {

        System.out.println("测试" + hello());

    }
}

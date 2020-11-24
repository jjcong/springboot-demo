package com.jincong.springboot.test.programmatic.bean;
/**
 * 使用纯编程式创建bean
 *
 * @author  j_cong
 * @date    2020/11/16
 * @version V1.0
 */
public class Person {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

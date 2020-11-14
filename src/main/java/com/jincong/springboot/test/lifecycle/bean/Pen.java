package com.jincong.springboot.test.lifecycle.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 使用Component方式的bean
 * PostConstruct注解相当于init方法
 * PreDestroy注解相当于destroy方法
 *
 * @author  j_cong
 * @date    2020/11/07
 * @version V1.0
 */
@Component
public class Pen {

    private Integer ink;

    public void setInk(Integer ink) {
        this.ink = ink;
    }



    @PostConstruct
    public void addInk() {
        System.out.println("钢笔中已加满墨水。。。");
        this.ink = 100;
    }

    @PreDestroy
    public void outwellInk() {
        System.out.println("钢笔中的墨水都放干净了。。。");
        this.ink = 0;
    }

    @Override
    public String toString() {
        return "Pen{" + "ink=" + ink + '}';
    }

}

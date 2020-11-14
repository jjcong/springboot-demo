package com.jincong.springboot.test.lifecycle;

import com.jincong.springboot.test.lifecycle.config.PenInitMethodConfigration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试Bean加载时间主启动类
 * 
 * @author  j_cong
 * @date    2020/11/07
 * @version V1.0
 */
public class PenInitMethodAnnoApplication {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("准备初始化IOC容器。。。");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PenInitMethodConfigration.class);
        System.out.println("IOC容器初始化完成。。。");
        System.out.println();
        System.out.println("准备销毁IOC容器。。。");
        ctx.close();
        System.out.println("IOC容器销毁完成。。。");
    }
}

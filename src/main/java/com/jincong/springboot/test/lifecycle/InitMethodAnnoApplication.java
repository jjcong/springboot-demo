package com.jincong.springboot.test.lifecycle;

import com.jincong.springboot.test.lifecycle.config.InitMethodConfigration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 测试Bean加载时间主启动类
 * 
 * @author  j_cong
 * @date    2020/11/07
 * @version V1.0
 */
public class InitMethodAnnoApplication {
    public static void main(String[] args) throws InterruptedException {
        //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(InitMethodConfigration.class);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(InitMethodConfigration.class);
        System.out.println("IOC容器初始化完成。。。。。");
        Thread.sleep(3000);
        ctx.close();
    }
}

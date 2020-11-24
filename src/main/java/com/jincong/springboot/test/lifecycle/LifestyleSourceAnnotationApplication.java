package com.jincong.springboot.test.lifecycle;

import com.jincong.springboot.test.lifecycle.bean.Cat;
import com.jincong.springboot.test.lifecycle.bean.Person;
import com.jincong.springboot.test.lifecycle.config.LifecycleDestructionPostProcessor;
import com.jincong.springboot.test.lifecycle.config.LifecycleNameReadPostProcessor;
import com.jincong.springboot.test.lifecycle.config.LifestyleSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试ban的生命周期启动类
 * 
 * @author  j_cong
 * @date    2020/11/18
 * @version V1.0
 */
public class LifestyleSourceAnnotationApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(LifestyleSourceConfiguration.class);
        ctx.register(LifecycleNameReadPostProcessor.class);
        ctx.register(LifecycleDestructionPostProcessor.class);

        System.out.println("================准备刷新IOC容器==================");
        ctx.refresh();
        System.out.println("================IOC容器刷新完毕==================");

        ctx.start();

        System.out.println("================IOC容器启动完成==================");

        Person person = ctx.getBean(Person.class);
        System.out.println(person);
        Cat cat = ctx.getBean(Cat.class);
        System.out.println(cat);

        System.out.println("================准备停止IOC容器==================");

        ctx.stop();

        System.out.println("================IOC容器停止成功==================");

        ctx.close();



    }
}

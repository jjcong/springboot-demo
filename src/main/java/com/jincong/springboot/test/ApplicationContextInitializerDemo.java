package com.jincong.springboot.test;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 测试ApplicationContextInitializer，在容器初始化之前的回调
 * 1、实现 ApplicationContextInitializer接口，在运行时注入
 * 2、在application.properties增加context.initializer.classes=com.example.demo.ApplicationContextInitializerDemo
 * 3、在工程的 resources 目录下新建 “META-INF” 目录，并在下面创建一个 spring.factories 文件。在文件内声明：
 * org.springframework.context.ApplicationContextInitializer=com.example.demo.ApplicationContextInitializerDemo
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/01/20
 */
public class ApplicationContextInitializerDemo implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("ApplicationContextInitializerDemo#run......");
    }
}

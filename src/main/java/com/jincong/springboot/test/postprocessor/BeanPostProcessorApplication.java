package com.jincong.springboot.test.postprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 测试BeanPostProcessor初始化时机
 *
 * @author  j_cong
 * @date    2020/11/15
 * @version V1.0
 */
public class BeanPostProcessorApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                "com.jincong.springboot.test.postprocessor");
        ctx.close();
    }
}

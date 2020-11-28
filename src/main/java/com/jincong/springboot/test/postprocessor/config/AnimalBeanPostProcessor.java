package com.jincong.springboot.test.postprocessor.config;

import com.jincong.springboot.test.postprocessor.Cat1;
import com.jincong.springboot.test.postprocessor.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 测试beanPostProcessor
 *
 * @author  j_cong
 * @date    2020/11/15
 * @version V1.0
 */
@Component
public class AnimalBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)  throws BeansException {
        System.out.println("拦截到Bean的初始化之前：" + bean);
        if (bean instanceof Cat1) {
            return new Dog();
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("拦截到Bean的初始化之后：" + bean);
        return bean;
    }
}

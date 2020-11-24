package com.jincong.springboot.test.lifecycle.config;

import com.jincong.springboot.test.lifecycle.bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Person的后置处理器
 * 
 * @author  j_cong
 * @date    2020/11/18
 * @version V1.0
 */
public class LifecycleNameReadPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            Person person = (Person) bean;
            System.out.println("LifecycleNameReadPostProcessor ------> " + person.getName());
        }
        return bean;
    }
}

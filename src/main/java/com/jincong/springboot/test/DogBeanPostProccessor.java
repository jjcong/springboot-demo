package com.jincong.springboot.test;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DogBeanPostProccessor implements BeanPostProcessor {

 /*   @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Dog) {
            Dog dog = (Dog) bean;
            dog.name = "My cat";
        }
        return bean;
    }*/

}

package com.jincong.springboot.test.lifecycle.config;

import com.jincong.springboot.test.lifecycle.bean.Cat;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
/**
 * Bean销毁时的后置处理器
 * 
 * @author  j_cong
 * @date    2020/11/18
 * @version V1.0
 */
public class LifecycleDestructionPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (bean instanceof Cat) {
            Cat cat = (Cat) bean;
            //System.out.println(cat.getName() + "被放走了 ......");
        }
    }
}

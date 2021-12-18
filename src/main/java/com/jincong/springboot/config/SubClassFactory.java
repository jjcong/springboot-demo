package com.jincong.springboot.config;

import com.jincong.springboot.service.Parent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j_cong
 * @version V1.0
 * @date 2021/12/14
 */
@Component
public class SubClassFactory implements ApplicationContextAware {

    private static Map<String, Parent> map = new HashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Parent> beansOfType = applicationContext.getBeansOfType(Parent.class);

        beansOfType.forEach((key, value) -> map.put(value.getType(), value));
        System.out.println("map = " + map);
    }


    public static  Parent getMap(String type) {
        return map.get(type);
    }
}

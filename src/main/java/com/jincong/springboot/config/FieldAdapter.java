package com.jincong.springboot.config;

import com.alibaba.fastjson.JSON;
import com.jincong.springboot.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * FieldAdapter 通用的字段适配器
 * 应用场景：将多个不同对象的字段映射成统一的字段
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/5/16
 */
public class FieldAdapter {

    public static User filter(String objJson, Map<String, String> target) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map objMap = JSON.parseObject(objJson, Map.class);

        User user = new User();

        for (String key : target.keySet()) {
            Object val = objMap.get(target.get(key));
            User.class.getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class)
                    .invoke(user, val.toString());

        }

        return user;
    }

}

package com.jincong.springboot.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis服务公共放方法
 *
 * @author j_cong
 * @version V1.0
 * @date 2019/08/10
 */
@Service
public class RedisTemplateService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public <T> boolean set(String key, T value) {

        try {

            //任意类型转换成String
            String val = beanToString(value);

            if (StringUtils.isEmpty(val)) {
                return false;
            }

            stringRedisTemplate.opsForValue().set(key, val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public  boolean addZset(String key, String value , Double score) {

        try {

            //任意类型转换成String
            String val = beanToString(value);

            if (StringUtils.isEmpty(val)) {
                return false;
            }

            stringRedisTemplate.opsForZSet().add(key, value, score);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Set<String> range(String key, double min, double max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }


    public Long deleteZset(String key, Object... values) {
        return stringRedisTemplate.opsForZSet().remove(key, values);
    }
    public <T> boolean setIfAbsent(String key, T value, long timeout, TimeUnit timeUnit) {

        try {

            //任意类型转换成String
            String val = beanToString(value);

            if (StringUtils.isEmpty(val)) {
                return false;
            }
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, val, timeout, timeUnit);
            if (aBoolean != null) {
                return aBoolean;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            String value = stringRedisTemplate.opsForValue().get(key);

            return stringToBean(value, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String value, Class<T> clazz) {
        if (value == null || value.length() <= 0 || clazz == null) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(value);
        } else if (clazz == String.class) {
            return (T) value;
        } else {
            return JSON.toJavaObject(JSON.parseObject(value), clazz);
        }
    }


    /**
     * Bean转化为String
     *
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {

        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }
    }
}

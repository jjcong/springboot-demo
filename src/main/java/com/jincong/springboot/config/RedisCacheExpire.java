package com.jincong.springboot.config;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

import static com.jincong.springboot.config.TimeType.SECONDS;

/**
 * RedisCacheDuration
 * 自定义注解，实现redis指定空间设置不同的过期时间
 * 默认60s
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/13
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCacheExpire {

    /**
     * expire time, default 60s
     */
    @AliasFor("expire")
    long value() default 60L;

    /**
     * expire time, default 60s
     */
    @AliasFor("value")
    long expire() default 60L;


    /**
     * 时间单位 默认为秒
     */
    TimeType type() default SECONDS;
}

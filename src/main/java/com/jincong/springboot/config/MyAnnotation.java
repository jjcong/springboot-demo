package com.jincong.springboot.config;

import java.lang.annotation.*;

/**
 * MyAnnotation
 * 自定义注解，实现访问的权限校验
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/3/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
}

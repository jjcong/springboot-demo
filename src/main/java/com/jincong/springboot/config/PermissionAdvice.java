package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * PermissionAdvice
 * 权限切面类
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/3/5
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class PermissionAdvice {

    // 扫描指定的注解，实现权限校验
    @Pointcut("@annotation(com.jincong.springboot.config.MyAnnotation)")
    private void checkPermission() {

    }

    @Before("checkPermission()")
    public void permisssion() {
        log.info("开始权限校验。。。。。。。");
    }


}

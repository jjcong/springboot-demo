package com.jincong.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 使用AOP统一处理Web请求日志
 * 执行顺序
 *  环绕通知start->前置通知->环绕通知end->后置通知->最终通知
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/03/09
 */
@Aspect
@Component
public class WebControllerAop {
    /**
     * 指定切入点，匹配 com.jincong.springboot.controller包及其子包下的所有类的所有方法
     */
    @Pointcut("execution(public * com.jincong.springboot.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("前置通知开始——————————————————————");
        // 获取目标方法的参数
        Signature signature = joinPoint.getSignature();
        // 代理方法
        System.out.println("代理方法为： " + signature.getName());
        // AOP代理类的名称
        System.out.println("代理类所在的包： " + signature.getDeclaringTypeName());
        // AOP代理类的信息
        signature.getDeclaringType();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] paramsArr = methodSignature.getParameterNames();
        System.out.println("参数名： " + Arrays.toString(paramsArr));
        System.out.println("参数值： " + Arrays.toString(joinPoint.getArgs()));
        //接收到的请求，记录请求内容

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println("请求URL： " + request.getRequestURI().toString());
        System.out.println("请求方式： " + request.getMethod());
        System.out.println("请求IP： " + request.getRemoteAddr());
        System.out.println("请求类方法： " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        System.out.println("前置通知结束——————————————————————");
    }


    /**
     * 处理完请求返回内容
     *
     * @param result
     * @throws Throwable
     */
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable {
        // 处理完请求，返回内容、
        Object instacnce = result;
        if (instacnce instanceof List) {
            ((List) instacnce).stream().forEach(System.out::println);
        } else {
            System.out.println("方法的返回值 : " + instacnce);
        }

    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
        System.out.println("异常通知开始——————————————————————");

        System.out.println("异常通知结束——————————————————————");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param jp
     */
    @After("webLog()")
    public void after(JoinPoint jp) {
        System.out.println("后置通知开始——————————————————————");

        System.out.println("后置通知结束——————————————————————");

    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     *
     * @param pjp
     * @return
     */
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {

        try {
            System.out.println("环绕通知开始——————————————————————");
            Object o = pjp.proceed();

            System.out.println("环绕通知结束——————————————————————");
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

}

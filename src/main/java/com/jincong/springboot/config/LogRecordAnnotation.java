package com.jincong.springboot.config;

import java.lang.annotation.*;

/**
 * 用户操作日志注解
 * @author j_cong
 * @version V1.0
 * @date 2022/1/1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecordAnnotation {


    /**
     * 操作日志的文本模板 * 必填
     * @return
     */
    String success();


    /**
     * 操作日志失败的文本模板
     * @return
     */
    String fail() default "";


    /**
     * 操作日志的执行人
     * @return
     */
    String operator() default "";


    /**
     * 操作日志绑定的业务对象编号 * 必填
     * @return
     */
    String bizNo();


    /**
     * 操作日志的种类
     * @return
     */
    String category() default "";


    /**
     * 操作日志的详情内容
     * @return
     */
    String detail() default "";


    /**
     * 操作日志的条件
     * @return
     */
    String condition() default "";
}

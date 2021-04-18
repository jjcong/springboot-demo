package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;

import javax.servlet.ServletConfig;

/**
 * StartedService
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/4/18
 */
@Slf4j
@Component
public class StartedWithServletConfigAware implements ServletConfigAware {


    /**
     * 在填充普通bean属性之后但在初始化之前调用
     * 类似于initializingBean的afterPropertiesset或自定义init方法的回调
     *
     */
    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        log.info("执行StartedService方法。。。。。。。。。");
    }
}

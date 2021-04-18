package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * StartedService
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/4/18
 */
@Slf4j
@Component
public class StartedWithServletContextListener implements ServletContextListener {
    /**
     * 在初始化Web应用程序中的任何过滤器或servlet之前，将通知所有servletContextListener上下文初始化。
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("执行contextInitialized方法");
    }

}

package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * StartedService
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/4/18
 */
@Slf4j
@Component
public class StartedWithApplicationRunner implements ApplicationRunner {


    /**
     * 用于指示bean包含在SpringApplication中时应运行的接口。可以定义多个applicationRunner bean
     * 在同一应用程序上下文中，可以使用有序接口或@order注释对其进行排序。
     *  该方法会在应用启动后执行
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("执行StartedWithApplicationRunner方法");
    }
}

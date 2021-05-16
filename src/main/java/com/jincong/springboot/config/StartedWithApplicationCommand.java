package com.jincong.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
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
@Order(value = 1)
public class StartedWithApplicationCommand implements CommandLineRunner {


    /**
     * 用于指示bean包含在SpringApplication中时应运行的接口。可以在同一应用程序上下文中定义多个commandLineRunner bean，
     * 并且可以使用有序接口或@order注释对其进行排序。
     * 如果需要访问applicationArguments而不是原始字符串数组，请考虑使用applicationRunner。
     *
     */

    @Override
    public void run(String... args) throws Exception {
        log.info("执行StartedWithApplicationCommand");
    }
}

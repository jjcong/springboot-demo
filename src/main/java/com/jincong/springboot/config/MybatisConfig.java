package com.jincong.springboot.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 注册Mybatis插件
 *
 * @author  j_cong
 * @date    2020/06/13
 * @version V1.0
 */
@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            MybatisInterceptor myPlugin = new MybatisInterceptor();
            Properties properties = new Properties();
            // 为了测试方便，直接设置慢查询阈值为1ms
            properties.setProperty("time", "1");
            myPlugin.setProperties(properties);
            configuration.addInterceptor(myPlugin);
        };
    }
}

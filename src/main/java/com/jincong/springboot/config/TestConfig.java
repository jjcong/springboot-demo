package com.jincong.springboot.config;

import com.jincong.springboot.domain.Food;
import com.jincong.springboot.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * TestConfig  测试@Configration注解
 *
 * 向容器中注册bean，功能与之前的<bean></bean>类似，通常配合@Bean注解使用
 *
 * 本身就是一个bean，内部配合proxyBeanMethods来解决组件依赖问题
 *
 * - Full （proxyBeanMethods= true）保证每个Bean方法被调用时都是单实例，是CGLIB代理生成的bean对象
 * - Lite（proxyBeanMethods= false）每个Bean方法被调用时都是多实，普通的bean对象
 *
 * bean注解指定向容器中注册一个bean对象，beanName为方法名，beanClass=返回值类型，默认产生的是单例bean
 *
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/1/9
 */
@Configuration
@Import({User.class})
//@ConditionalOnBean(name="tom")
//@EnableConfigurationProperties(Food.class)


public class TestConfig {

    @Bean
    public Food food() {
        return Food.builder().name("香蕉").price(12).build();
    }
}

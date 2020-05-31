package com.jincong.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author j_cong
 */
@SpringBootApplication
// SpringBootApplication 注释相当于@Configuration @ComponentScan, @SpiingBootConfigration三个注解
@MapperScan("com.jincong.springboot.mapper")
// 注意，ComponentScan注解指定扫描的包，如果指定的包没有Controller注解，访问无效
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableSwagger2

public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
       /* SpringApplication springApplication = new SpringApplication(SpringbootApplication.class);
        springApplication.addInitializers(new ApplicationContextInitializerDemo());
        springApplication.run(args);*/
    }

}

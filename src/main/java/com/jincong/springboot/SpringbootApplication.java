package com.jincong.springboot;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author j_cong
 */
@SpringBootApplication
// SpringBootApplication 注释相当于@Configuration @ComponentScan, @SpiingBootConfigration三个注解
@MapperScan("com.jincong.springboot.mapper")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}

package com.jincong.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author j_cong
 * @version V1.0
 * @date 2020/04/05
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                //指定构建API文档的详细信息的具体方法
                .apiInfo(apiInfo())
                .select()
                //指定Swagger扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.jincong.springboot.controller"))
                .paths(PathSelectors.any())
                .build();

    }


    /**
     * 构建Api文档的详细信息
     *
     * @return apiinfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot基础Swagger2接口总览")
                .description("SpringBoot")
                .contact("bigger")
                .version("1.0")
                .build();
    }
}

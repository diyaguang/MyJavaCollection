package com.dygstudio.testspringboot2.config;

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
 * @author: diyaguang
 * @date: 2019/03/12 12:59 PM
 * @description: com.dygstudio.testspringboot2.config
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.dygstudio.testspringboot2.api")).paths(PathSelectors.any()).build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("springboot 利用 swagger 构建 api 文档").description("简单优雅的 restfun 风格，http://xxxxxxx").termsOfServiceUrl("http://my.dygstudio.cn").version("1.0").build();
    }
}

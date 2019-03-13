package com.dygstudio.testspringboot2;

import com.dygstudio.testspringboot2.config.StorageProperties;
import com.dygstudio.testspringboot2.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
public class Testspringboot2Application {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Testspringboot2Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService){
        return args -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            //System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for(String beanName:beanNames){
                //System.out.println(beanName);
            }

            //测试 使用 restTemplate
            //String quote = ((RestTemplate)ctx.getBean("restTemplate")).getForObject("http://gturnquist-quoters.cfapps.io/api/random",String.class);
            //System.out.println(quote );


        };
    }

}

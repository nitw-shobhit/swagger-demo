package com.swagger.demo;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@SpringBootApplication(scanBasePackages="com.swagger")
@EnableConfigurationProperties()
@EnableSwagger2
@EnableCaching(proxyTargetClass = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket newsApi() {
        Docket dock = new Docket(DocumentationType.SWAGGER_2)
                .groupName("Say Hello")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/sayHello*"))
                .build();
    	return dock;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("APPGRID-SWAGGER")
                .description("SWAGGER DEMO")
                .termsOfServiceUrl("www.accedo.tv")
                .contact("Shobhit Tyagi")
                .license("Apache License Version 2.0")
                .licenseUrl("")
                .version("2.0")
                .build();
    }
}
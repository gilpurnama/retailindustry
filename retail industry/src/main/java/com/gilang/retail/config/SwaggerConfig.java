package com.gilang.retail.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SPRING_WEB.SWAGGER_2)
                .select()       // give control over endpoints to exposed via swagger
                .apis(RequestHandlerSelectors.any())        // it will make documentation for entire API that available
                .paths(Predicates.not(PathSelectors.regex("/error.*")))     // to shows it at UI and to remove basic-error-controller on Swagger UI
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Retail Industry RET API")
                .description("Spring Boot REST API")
                .version("1.0.0")
                .build();
    }
}
package com.example.etsproject.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return (new Docket(DocumentationType.SWAGGER_2).select().
                apis(RequestHandlerSelectors.basePackage("com.example.etsproject"))).
                paths(PathSelectors.regex("/.*")).build().
                apiInfo(apiEndPointInfo());
    }

    public ApiInfo apiEndPointInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot swagger example").
                description("bl bl bl").contact(new Contact("ETS Tour", "https://etstur.com", "info@etstur.com")).
                license("Apache 2.0").
                licenseUrl("").
                version("1.3.3.7").build();
    }
}

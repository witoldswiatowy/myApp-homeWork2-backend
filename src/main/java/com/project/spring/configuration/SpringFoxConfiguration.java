package com.project.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author Paweł Recław, AmeN
 * @project project_spring
 * @created 10.09.2022
 */
@Configuration
public class SpringFoxConfiguration {

    // http://localhost:8080/swagger-ui/index.html
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                // Documentation
                .apiInfo(new ApiInfo(
                        "Generic Spring Project",
                        "Generic Java Spring Security service with Angular UI",
                        "1.0",
                        null,
                        new Contact("Mr. Robot", "https://www.whoismrrobot.com/", "will.i.am@mail.com"),
                        null,
                        null,
                        Collections.emptyList()
                ))
                .select()
                // Configuration
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.project.spring"))
                .build();
    }
}

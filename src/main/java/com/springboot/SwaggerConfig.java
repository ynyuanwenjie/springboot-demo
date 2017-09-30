package com.springboot;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by yuanwenjie on 2017/9/12.
 */
@Configuration
@EnableSwagger2
//@Profile("dev")
public class SwaggerConfig {

    @Bean
    public Docket thirdApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("spring boot demo API")
                //.genericModelSubstitutes(ReservationController.class)
                //.genericModelSubstitutes(Reservation.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error"))))
                .build()
                .apiInfo(apiInfo());


    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                "ynyuanwenjie@gamil.com",
                "License of API",
                "API license URL");
        return apiInfo;
    }

}

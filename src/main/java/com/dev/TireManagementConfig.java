package com.dev;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2
public class TireManagementConfig {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Jurek Nitschky", "", "Jurek.nit@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "TireManagement REST API", "Backend for Land Motorsports Tire-Management system", "1.0",
            "", DEFAULT_CONTACT,
            "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0", List.of());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(List.of("application/json"));

    private static final String HOST_NAME = "limla.ml:8080";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .host("limla.ml");
    }

}

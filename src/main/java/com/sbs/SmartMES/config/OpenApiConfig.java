package com.sbs.SmartMES.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.*;
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smartMESOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MES 자동화 시스템 API")
                        .description("Spring Boot 기반 MES API 문서")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("개발자")
                                .email("dev@smartmes.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("all-api")  // 한글 → 영어로 변경
                .pathsToMatch("/api/**")
                .build();
    }
}

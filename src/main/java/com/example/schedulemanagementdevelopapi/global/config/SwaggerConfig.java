package com.example.schedulemanagementdevelopapi.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("일정 관리 API")
                .description("API 명세서")
                .version("v1.0");

        return new OpenAPI()
                .info(info);

    }
}

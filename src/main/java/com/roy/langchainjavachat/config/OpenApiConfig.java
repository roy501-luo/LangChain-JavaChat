package com.roy.langchainjavachat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("langChainJavaChat-接口文档")
                        .description("#langChainJavaChat相关API接口")
                        .version("v1")
                );
    }
}

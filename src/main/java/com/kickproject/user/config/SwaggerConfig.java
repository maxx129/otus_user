package com.kickproject.user.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public GroupedOpenApi publicUserApi() {
//        return GroupedOpenApi
//                .builder()
//                .group("Juridical")
//                .pathsToMatch("/juridical/**")
//                .build();
//    }

    @Bean
    public OpenAPI customOpenApi(@Value("""
            Во всех методах необходима аутентификация, для этого первым вызывается метод /authentication,\n
            куда передается логин и пароль. В ответе возвращается JWT-токен, который необходимо передавать во все\n
            последующие методы в заголовке Authorization. Для выполнения запросов через Swagger этот токен нужно\n
            ввести в плашке с замком Authorize, либо через пиктограмму с замком у конкретного метода.
            """)String appDescription, @Value("v0")String appVersion) {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI().info(new Info().title("API МП Банк")
                        .version(appVersion)
                        .description(appDescription)
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact().name("username")
                                .email("test@gmail.com")))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}

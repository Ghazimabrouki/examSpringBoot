package com.arctic.ghazi_mabrouki_4_ArcTic3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Test Spring")
                        .version("1.0")
                        .description("SpringBoot Test")
                        .contact(new Contact()
                                .name("Ghazi Mabrouki")
                                .email("ghazi.mabrouki@esprit.tn")));
    }
}

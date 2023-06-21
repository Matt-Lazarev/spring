package com.lazarev.springswagger.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi testApiV1(){
        return GroupedOpenApi.builder()
                .group("API V1")
                .pathsToMatch("/api/v1/**")
                .addOpenApiCustomiser(customizer ->
                        customizer.setInfo(
                                getInfo("Test Controller V1", "1.0", "API-V1")))
                .build();
    }

    @Bean
    public GroupedOpenApi testApiV2(){
        return GroupedOpenApi.builder()
                .group("API V2")
                .pathsToMatch("/api/v2/**")
                .addOpenApiCustomiser(customizer ->
                        customizer.setInfo(
                                getInfo("Test Controller V2", "2.0", "API-V2")))
                .build();
    }


    private Info getInfo(String title, String version, String description) {
        return new Info()
                .title(title)
                .version(version)
                .description(description);
    }
}

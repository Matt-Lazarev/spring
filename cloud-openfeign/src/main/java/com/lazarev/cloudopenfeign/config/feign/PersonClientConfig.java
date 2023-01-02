package com.lazarev.cloudopenfeign.config.feign;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazarev.cloudopenfeign.exception.NoSuchPersonException;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;

import java.util.Map;

public class PersonClientConfig {

    @Bean
    public ErrorDecoder personClientErrorDecoder() {
        return (s, response) -> {
            if(response.status() == 401){
                return new AccessDeniedException("Provide HttpBasic authentication");
            }
            if (response.status() == 404) {
                Map<String, Object> errors = getResponseBody(response);
                return new NoSuchPersonException(String.valueOf(errors.get("message")));
            }
            return new Exception(response.reason());
        };
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${person-service.username") String username,
            @Value("${person-service.password") String password){
        return new BasicAuthRequestInterceptor(username, password);
    }

    @SneakyThrows
    private Map<String, Object> getResponseBody(Response response){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body().asInputStream(), new TypeReference<>() {});
    }
}

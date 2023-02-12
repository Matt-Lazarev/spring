package com.lazarev.securityoauth2client.controller;

import com.lazarev.securityoauth2client.config.HelloFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class TestController {
    private final WebClient webClient;
    private final HelloFeignClient helloFeignClient;

    @GetMapping("/webclient/hello")
    public String webClientRequest() {
        return webClient
                .get()
                .uri("http://localhost:8080/api/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/feign/hello")
    public String feignClientRequest() {
        return helloFeignClient.getHelloResponse();
    }

    @GetMapping("/test")
    public String getTestData(){
        return "Success";
    }
}


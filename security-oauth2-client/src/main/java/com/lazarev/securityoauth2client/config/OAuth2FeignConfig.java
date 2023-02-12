package com.lazarev.securityoauth2client.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class OAuth2FeignConfig {
    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Value("${spring.security.oauth2.client.registration.my-client.client-name}")
    public String clientId;

    @Bean
    public RequestInterceptor requestInterceptor() {
        ClientRegistration client = clientRegistrationRepository.findByRegistrationId(clientId);
        return request ->
                request.header("Authorization", "Bearer " + getAccessToken(client));
    }

    private String getAccessToken(ClientRegistration clientRegistration) {
        OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(clientRegistration.getRegistrationId())
                .principal(SecurityContextHolder.getContext().getAuthentication())
                .build();
        OAuth2AuthorizedClient client = authorizedClientManager.authorize(oAuth2AuthorizeRequest);
        if (Objects.isNull(client)) {
            throw new IllegalStateException("Authorization failed for client " + clientRegistration.getClientId());
        }
        return client.getAccessToken().getTokenValue();
    }
}

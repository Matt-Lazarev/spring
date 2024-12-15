package com.lazarev.webhookservice.client;

import com.lazarev.webhookservice.dto.WebhookNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebhookNotificationClient {
    private final RestClient restClient;

    public void notifyClient(WebhookNotification webhookNotification) {
        try {
            restClient.post()
                    .uri(webhookNotification.endpointUrl())
                    .contentType(APPLICATION_JSON)
                    .body(webhookNotification)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception ex) {
            log.error(
                    "Error while sending a webhook-notification to {} for payment {}",
                    webhookNotification.endpointUrl(),
                    webhookNotification.paymentId()
            );
        }
    }
}

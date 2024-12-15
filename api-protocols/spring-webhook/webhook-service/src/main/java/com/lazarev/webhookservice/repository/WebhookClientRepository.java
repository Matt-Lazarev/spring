package com.lazarev.webhookservice.repository;

import com.lazarev.webhookservice.entity.WebhookClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WebhookClientRepository extends JpaRepository<WebhookClient, UUID> {

    Optional<WebhookClient> findByName(String name);
}

package com.lazarev.webhookservice.entity;

import com.lazarev.webhookservice.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "webhook_clients")
public class WebhookClient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", unique = true)
    private String name;

    private String endpointUrl;

    @JdbcTypeCode(SqlTypes.JSON)
    private Set<PaymentStatus> notificationStatuses;
}

package com.lazarev.webhookservice.mapper;

import com.lazarev.webhookservice.dto.PaymentDto;
import com.lazarev.webhookservice.dto.WebhookClientDto;
import com.lazarev.webhookservice.dto.WebhookNotification;
import com.lazarev.webhookservice.entity.Payment;
import com.lazarev.webhookservice.entity.WebhookClient;
import com.lazarev.webhookservice.entity.enums.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = PaymentStatus.class)
public interface PaymentWebhookMapper {

    PaymentDto toPaymentDto(Payment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(PaymentStatus.PENDING)")
    Payment toPayment(PaymentDto paymentDto, WebhookClient webhookClient);

    @Mapping(target = "paymentId", source = "payment.id")
    @Mapping(target = "newStatus", source = "payment.status")
    @Mapping(target = "message", expression = "java(payment.getStatus().message())")
    @Mapping(target = "endpointUrl", source = "payment.webhookClient.endpointUrl")
    WebhookNotification toWebhookNotification(Payment payment);

    WebhookClient toWebhookClient(WebhookClientDto dto);
}

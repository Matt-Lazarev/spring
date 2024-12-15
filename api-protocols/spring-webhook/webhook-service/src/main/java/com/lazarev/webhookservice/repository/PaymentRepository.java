package com.lazarev.webhookservice.repository;

import com.lazarev.webhookservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query("select p from Payment p left join fetch p.webhookClient where p.id = :id")
    Optional<Payment> findPaymentById(UUID id);
}

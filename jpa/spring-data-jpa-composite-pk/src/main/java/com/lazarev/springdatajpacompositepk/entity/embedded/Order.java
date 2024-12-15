package com.lazarev.springdatajpacompositepk.entity.embedded;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @EmbeddedId
    private OrderId orderId;

    private String name;
}

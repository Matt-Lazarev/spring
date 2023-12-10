package com.lazarev.springdatajpa.entity.composite;

import com.lazarev.springdatajpa.entity.composite.OrderId;
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
    private OrderId orderID;

    private String name;
}

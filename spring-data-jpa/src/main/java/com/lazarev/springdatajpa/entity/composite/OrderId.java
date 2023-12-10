package com.lazarev.springdatajpa.entity.composite;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class OrderId implements Serializable {
    private Integer orderId;

    private Integer systemId;
}

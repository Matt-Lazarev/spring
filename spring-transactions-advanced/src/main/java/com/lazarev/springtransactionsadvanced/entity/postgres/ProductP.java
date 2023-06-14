package com.lazarev.springtransactionsadvanced.entity.postgres;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class ProductP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer amount;

    @Version
    private Integer version;
}

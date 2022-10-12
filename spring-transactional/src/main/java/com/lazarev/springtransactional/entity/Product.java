package com.lazarev.springtransactional.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name="products")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private Integer price;
}

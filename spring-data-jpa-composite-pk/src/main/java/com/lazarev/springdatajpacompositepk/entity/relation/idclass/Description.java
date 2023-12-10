package com.lazarev.springdatajpacompositepk.entity.relation.idclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "descriptions")
@IdClass(DescriptionId.class)
public class Description {
    @Id
    @GeneratedValue
    private Integer descriptionId;

    @Id
    private UUID ownerId;

    private String text;
}

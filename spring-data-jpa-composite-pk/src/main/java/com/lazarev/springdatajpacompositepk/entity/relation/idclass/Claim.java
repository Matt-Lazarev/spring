package com.lazarev.springdatajpacompositepk.entity.relation.idclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "description_id", referencedColumnName = "descriptionId"),
            @JoinColumn(name = "owner_id", referencedColumnName = "ownerId")
    })
    private Description description;
}

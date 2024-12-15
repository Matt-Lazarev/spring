package com.lazarev.springdatajpacompositepk.entity.relation.emdedded;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "passport_number", referencedColumnName = "number"),
            @JoinColumn(name = "passport_series", referencedColumnName = "series")
    })
    private Passport passport;
}

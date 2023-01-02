package com.lazarev.springrest.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;
    private String email;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH},
               fetch = FetchType.LAZY)
    private Department department;
}

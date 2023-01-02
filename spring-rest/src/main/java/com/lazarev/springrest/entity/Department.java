package com.lazarev.springrest.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
}

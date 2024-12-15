package com.lazarev.shortpolling.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private Integer id;

    private String name;

    private String message;
}

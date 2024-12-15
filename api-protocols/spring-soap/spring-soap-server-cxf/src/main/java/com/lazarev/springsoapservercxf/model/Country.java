package com.lazarev.springsoapservercxf.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Country {
    private String name;
    private int population;
    private String capital;
    private Currency currency;
}

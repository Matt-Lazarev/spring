package com.lazarev.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Task {
    private final int id;
    private final String name;
    private boolean ready;

    public void setReady() {
        this.ready = true;
    }
}

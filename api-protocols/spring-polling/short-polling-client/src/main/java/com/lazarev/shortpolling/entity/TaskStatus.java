package com.lazarev.shortpolling.entity;

import java.util.Set;

public enum TaskStatus {
    CREATED, PENDING, FINISHED;

    public static final Set<TaskStatus> NOT_FINISHED_STATUSES = Set.of(CREATED, PENDING);
}

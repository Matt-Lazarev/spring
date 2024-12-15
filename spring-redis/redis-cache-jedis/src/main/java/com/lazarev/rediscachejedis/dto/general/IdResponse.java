package com.lazarev.rediscachejedis.dto.general;

import com.lazarev.rediscachejedis.enums.Operation;

public record IdResponse (Integer id, Operation operation) { }

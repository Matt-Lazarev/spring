package com.lazarev.springfiles.model;

import java.math.BigDecimal;

public record AttachmentUploadResponse (
        String fileName,
        Long fileId,
        BigDecimal fileSizeMb) { }


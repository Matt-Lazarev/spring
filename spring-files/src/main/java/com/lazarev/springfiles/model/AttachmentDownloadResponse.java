package com.lazarev.springfiles.model;

public record AttachmentDownloadResponse(
        String fileName,
        String fileType,
        byte[] data) { }

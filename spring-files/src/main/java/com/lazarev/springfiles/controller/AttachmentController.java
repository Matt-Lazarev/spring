package com.lazarev.springfiles.controller;

import com.lazarev.springfiles.model.AttachmentDownloadResponse;
import com.lazarev.springfiles.model.AttachmentUploadResponse;
import com.lazarev.springfiles.service.AttachmentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        AttachmentUploadResponse response = attachmentService.uploadFile(file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable Long fileId){
        AttachmentDownloadResponse response = attachmentService.downloadFile(fileId);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + response.fileName())
                .contentType(MediaType.parseMediaType(response.fileType()))
                .body(response.data());
    }
}

package com.lazarev.springfiles.service;

import com.lazarev.springfiles.entity.Attachment;
import com.lazarev.springfiles.model.AttachmentDownloadResponse;
import com.lazarev.springfiles.model.AttachmentUploadResponse;
import com.lazarev.springfiles.repository.AttachmentRepository;
import com.lazarev.springfiles.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @SneakyThrows
    public AttachmentUploadResponse uploadFile(MultipartFile file) {
        Attachment attachment = Attachment.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .data(FileUtils.compress(file.getBytes()))
                .build();

        attachment = attachmentRepository.save(attachment);

        return new AttachmentUploadResponse(
                file.getOriginalFilename(), attachment.getId(),
                bytesToMegabytes(file.getSize()));
    }

    @SneakyThrows
    public AttachmentDownloadResponse downloadFile(Long fileId){
        Attachment attachment = attachmentRepository
                .findById(fileId)
                .orElseThrow(()->new FileNotFoundException("File with id = '%d' not found".formatted(fileId)));

        return new AttachmentDownloadResponse(
                attachment.getFileName(), attachment.getFileType(),
                FileUtils.decompress(attachment.getData()));
    }

    private BigDecimal bytesToMegabytes(long bytes){
        double megabytes = bytes / (1024. * 1024.);
        return BigDecimal.valueOf(megabytes).setScale(2, RoundingMode.HALF_UP);
    }
}

package com.lazarev.springfiles.repository;

import com.lazarev.springfiles.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository
        extends JpaRepository<Attachment, Long> {
}

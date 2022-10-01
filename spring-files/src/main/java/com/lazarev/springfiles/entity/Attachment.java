package com.lazarev.springfiles.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "attachments")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;
}

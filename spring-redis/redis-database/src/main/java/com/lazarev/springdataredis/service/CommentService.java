package com.lazarev.springdataredis.service;

import com.lazarev.springdataredis.dto.CommentDto;
import com.lazarev.springdataredis.model.Comment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {

    public Comment createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(UUID.randomUUID());
        comment.setText(commentDto.text());

        return comment;
    }
}

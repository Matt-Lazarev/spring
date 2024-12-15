package com.lazarev.springdataredis.controller;

import com.lazarev.springdataredis.dto.ArticleDto;
import com.lazarev.springdataredis.dto.ArticleInfoDto;
import com.lazarev.springdataredis.dto.AuthorDto;
import com.lazarev.springdataredis.dto.CommentDto;
import com.lazarev.springdataredis.service.ArticleService;
import com.lazarev.springdataredis.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable UUID id) {
        ArticleDto article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<ArticleInfoDto> getArticleInfoById(@PathVariable UUID id) {
        ArticleInfoDto article = articleService.getArticleInfoById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<ArticleDto> saveAuthor(@RequestBody ArticleDto articleDto) {
        ArticleDto article = articleService.createArticle(articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addCommentToArticle(@PathVariable UUID id,
                                                 @RequestBody CommentDto commentDto){
        articleService.addComment(id, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

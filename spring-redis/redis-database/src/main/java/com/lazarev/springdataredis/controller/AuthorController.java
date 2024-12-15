package com.lazarev.springdataredis.controller;

import com.lazarev.springdataredis.dto.AuthorDto;
import com.lazarev.springdataredis.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable UUID id) {
        AuthorDto author = authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDto author = authorService.createAuthor(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }
}

package com.lazarev.springdataredis.service;

import com.lazarev.springdataredis.dto.AuthorDto;
import com.lazarev.springdataredis.model.Author;
import com.lazarev.springdataredis.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorDto getAuthorById(UUID id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author with id = '%s' not found".formatted(id)));
        return new AuthorDto(author.getId(), author.getName());
    }

    public Author getAuthorReferenceById(UUID id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author with id = '%s' not found".formatted(id)));
    }

    public AuthorDto createAuthor(AuthorDto authorDto){
        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setName(authorDto.name());

        Author savedAuthor = authorRepository.save(author);
        return new AuthorDto(savedAuthor.getId(), savedAuthor.getName());
    }
}

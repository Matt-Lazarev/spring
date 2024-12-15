package com.lazarev.springdataredis.service;

import com.lazarev.springdataredis.dto.ArticleDto;
import com.lazarev.springdataredis.dto.ArticleInfoDto;
import com.lazarev.springdataredis.dto.AuthorDto;
import com.lazarev.springdataredis.dto.CommentDto;
import com.lazarev.springdataredis.model.Article;
import com.lazarev.springdataredis.model.Author;
import com.lazarev.springdataredis.model.Comment;
import com.lazarev.springdataredis.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthorService authorService;
    private final CommentService commentService;

    public ArticleDto getArticleById(UUID id){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article with id = '%s' not found".formatted(id)));
        return new ArticleDto(article.getId(), article.getName(), article.getAuthor().getId());
    }

    public ArticleInfoDto getArticleInfoById(UUID id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article with id = '%s' not found".formatted(id)));

        return new ArticleInfoDto(
                article.getId(),
                article.getName(),
                new AuthorDto(article.getAuthor().getId(), article.getAuthor().getName()),
                article.getComments().stream().map(c -> new CommentDto(c.getId(), c.getText())).toList()
        );
    }

    public ArticleDto createArticle(ArticleDto articleDto){
        Article article = new Article();
        article.setId(UUID.randomUUID());
        article.setName(articleDto.name());

        Author author = authorService.getAuthorReferenceById(articleDto.authorId());
        article.setAuthor(author);

        Article savedArticle = articleRepository.save(article);

        return new ArticleDto(savedArticle.getId(), savedArticle.getName(), author.getId());
    }

    public void addComment(UUID id, CommentDto commentDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article with id = '%s' not found".formatted(id)));

        Comment comment = commentService.createComment(commentDto);

        article.addComment(comment);

        articleRepository.save(article);
    }
}

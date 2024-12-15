package com.lazarev.springdataredis.repository;

import com.lazarev.springdataredis.model.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends CrudRepository<Article, UUID> {
    Optional<Article> findArticleByName(String name);
}

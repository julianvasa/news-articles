package com.articles.controllers;

import com.articles.domain.Article;
import com.articles.repository.ArticleRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PostArticles {

    private final ArticleRepository articleRepository;

    public PostArticles(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostMapping("article")
    @ApiOperation(value = "Insert a new article")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Article successfully saved"),
        @ApiResponse(code = 204, message = "Article was not saved"),
    })
    public ResponseEntity<Article> postArticle(@Valid @RequestBody Article article){
        try {
            articleRepository.save(article);
            return new ResponseEntity<>(article, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}

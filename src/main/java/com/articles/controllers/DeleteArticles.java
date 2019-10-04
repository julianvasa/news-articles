package com.articles.controllers;

import com.articles.domain.Article;
import com.articles.repository.ArticleRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteArticles {

    private final ArticleRepository articleRepository;

    public DeleteArticles(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @DeleteMapping("/article/{id}")
    @ApiOperation(value = "Delete an article")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Article successfully deleted"),
        @ApiResponse(code = 404, message = "Article not found"),
    })
    public ResponseEntity<Article> deleteArticle(@PathVariable("id") String id){
        Article articleToDelete = articleRepository.getOne(Long.valueOf(id));
        try {
            articleRepository.delete(articleToDelete);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch
        (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

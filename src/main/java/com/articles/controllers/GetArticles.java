package com.articles.controllers;

import com.articles.domain.Article;
import com.articles.exceptions.ArticleNotFoundException;
import com.articles.repository.ArticleRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GetArticles {

    private final ArticleRepository articleRepository;
    private Optional<Article> articleFound;

    public GetArticles(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @RequestMapping(value = "/articles")
    @ApiOperation(value = "Get a list of all articles")
    public List<Article> getArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    @GetMapping("/article/{id}")
    @ApiOperation(value = "Get a single article with the provided id")
    public Article getArticleById(@PathVariable("id") String id) throws ArticleNotFoundException {
        articleFound = articleRepository.findById(Long.valueOf(id));
        return articleFound.orElseThrow(() -> new ArticleNotFoundException(id));
    }
}

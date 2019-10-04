package com.articles.controllers;

import com.articles.domain.Article;
import com.articles.repository.ArticleRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/search", method = RequestMethod.GET)
public class SearchArticles {

    private final ArticleRepository articleRepository;

    public SearchArticles(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/author/firstname/{firstName}")
    @ApiOperation(value = "Search articles by author first name")
    public List<Article> getArticlesByAuthorFirstName(@PathVariable("firstName") String firstNameAuthor) {
        return articleRepository.findByauthors_firstName(firstNameAuthor);
    }

    @GetMapping("/author/lastname/{lastName}")
    @ApiOperation(value = "Search articles by author last name")
    public List<Article> getArticlesByAuthorLastName(@PathVariable("lastName") String lastNameAuthor) {
        return articleRepository.findByauthors_lastName(lastNameAuthor);
    }

    @GetMapping("/keyword/{keyword}")
    @ApiOperation(value = "Search articles by keyword")
    public List<Article> getArticlesByKeyword(@PathVariable("keyword") String keyword) {
        return articleRepository.findBykeywords_value(keyword);
    }

    @GetMapping("/published/{from}/{to}")
    @ApiOperation(value = "Search articles by published date ( from - to )")
    public List<Article> getArticlesByPublishedDate(@PathVariable("from") String from, @PathVariable("to") String to) {
        return articleRepository.findByPublishedDateBetween(Date.valueOf(from),Date.valueOf(to));
    }
}

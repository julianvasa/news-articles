package com.articles.repository;

import com.articles.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByHeader(String header);
    List<Article> findByauthors_firstName(String firstNameAuthor);
    List<Article> findByauthors_lastName(String lastNameAuthor);

    List<Article> findBykeywords_value(String keyword);

    List<Article> findByPublishedDateBetween(Date start, Date end);

}

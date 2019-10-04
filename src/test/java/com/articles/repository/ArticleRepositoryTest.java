package com.articles.repository;

import com.articles.domain.Article;
import com.articles.domain.Author;
import com.articles.domain.Keyword;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ArticleRepositoryTest {

    @Autowired
    private
    ArticleRepository articleRepository;

    @Before
    public void setUp(){
        // given
        Article article = new Article();
        article.setId(1L);
        article.setHeader("header");
        article.setText("text");
        article.setShortDescription("short description");
        Author author = Author.builder().firstName("First Name").lastName("Last Name").build();
        Set<Author> sampleAuthorList = Stream.of(author).collect(Collectors.toSet());
        article.setAuthors(sampleAuthorList);
        Keyword tech = Keyword.builder().value("tech").build();
        Keyword science = Keyword.builder().value("science").build();
        Keyword sports = Keyword.builder().value("sports").build();
        Set<Keyword> sampleKeywords = Stream.of(tech, science, sports).collect(Collectors.toSet());
        article.setKeywords(sampleKeywords);
        articleRepository.save(article);
    }

    @Test
    public void whenFindAll_thenReturnArticleListSize() {
        assertEquals(1, articleRepository.findAll().size());
    }

    @Test
    public void whenInsertArticle_thenReturnAuthorsSize(){
        Article article = articleRepository.getOne(1L);
        List<Author> actualAuthors = new ArrayList<>(article.getAuthors());
        assertEquals(1, actualAuthors.size());
    }

    @Test
    public void whenInsertArticle_thenReturnAuthorFirstNameAndLastName(){
        Article article = articleRepository.getOne(1L);
        List<Author> actualAuthors = article.getAuthors().stream().filter(author -> author.getFirstName().equals("First Name")).collect(Collectors.toList());
        assertEquals(1, actualAuthors.size());
    }

    @Test
    public void whenInsertArticle_thenReturnKeywordsSize(){
        Article article = articleRepository.getOne(1L);
        List<Keyword> actualKeywords = new ArrayList<>(article.getKeywords());
        assertEquals(3, actualKeywords.size());
    }

    @Test
    public void whenInsertArticle_thenReturnKeywords(){
        Article article = articleRepository.getOne(1L);
        List<Author> actualAuthors = article.getAuthors().stream().filter(author -> author.getFirstName().equals("First Name")).collect(Collectors.toList());
        assertEquals(1, actualAuthors.size());
    }

    @Test
    public void whenInsertArticle_thenReturnHeader(){
        Article article = articleRepository.getOne(1L);
        String actualHeader = article.getHeader();
        assertEquals("header", actualHeader);
    }

    @Test
    public void whenInsertArticle_thenReturnText(){
        Article article = articleRepository.getOne(1L);
        String actualText = article.getText();
        assertEquals("text", actualText);
    }

    @Test
    public void whenInsertArticle_thenReturnShortDescription(){
        Article article = articleRepository.getOne(1L);
        String actualShortDescription = article.getShortDescription();
        assertEquals("short description", actualShortDescription);
    }
}

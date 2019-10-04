package com.articles.controllers;

import com.articles.domain.Article;
import com.articles.domain.Author;
import com.articles.domain.Keyword;
import com.articles.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SearchArticlesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Before
    public void setUp() throws ParseException {
        // given
        Article article = new Article();
        article.setId(1L);
        article.setHeader("header");
        article.setText("text");
        String published="Tue, Oct 1 2019 23:37:50";
        Date published_dt=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss").parse(published);
        article.setPublishedDate(published_dt);
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
    public void whenSearchArticlesByAuthorFirstName_thenReturnArticles() throws Exception {
        mockMvc.perform(get("/search/author/firstname/First Name").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void whenSearchArticlesByAuthorLastName_thenReturnArticles() throws Exception {
        mockMvc.perform(get("/search/author/lastname/Last Name").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void whenSearchArticlesByKeyword_thenReturnArticles() throws Exception {
        mockMvc.perform(get("/search/keyword/tech").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void whenSearchArticlesByDate_thenReturnArticles() throws Exception {
        mockMvc.perform(get("/search/published/2019-10-01/2019-10-02").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1));
    }
}

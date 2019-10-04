package com.articles.controllers;

import com.articles.domain.Article;
import com.articles.domain.Author;
import com.articles.domain.Keyword;
import com.articles.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class GetArticlesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() throws ParseException {
        // given
        Article article = new Article();
        article.setId(1L);
        article.setHeader("header");
        article.setText("text");
        String published = "Tue, Oct 1 2019 23:37:50";
        Date published_dt = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss").parse(published);
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
    public void getArticles_thenReturnResponse200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void getArticlesSize() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void whenGetArticles_thenReturnHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].header").isNotEmpty());
    }

    @Test
    public void whenGetArticles_thenReturnShortDescription() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].shortDescription").isNotEmpty());
    }

    @Test
    public void whenGetArticles_thenReturnText() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].text").isNotEmpty());
    }

    @Test
    public void whenGetArticles_thenReturnPublishedDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[0]").exists())
            .andExpect(jsonPath("$.[0].publishedDate").isNotEmpty());
    }

    @Test
    public void whenGetArticles_thenReturnAuthors() throws Exception {
        MvcResult result = mockMvc.perform(
            get("/articles")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Article[] articles = mapper.readValue(content, Article[].class);
        List<Author> authors = new ArrayList<>(articles[0].getAuthors());
        assertEquals("First Name", authors.get(0).getFirstName());
        assertEquals("Last Name", authors.get(0).getLastName());
    }

    @Test
    public void whenGetArticles_thenReturnKeywords() throws Exception {
        MvcResult result = mockMvc.perform(
            get("/articles")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Article[] articles = mapper.readValue(content, Article[].class);
        List<Keyword> keywords = new ArrayList<>(articles[0].getKeywords());
        assertEquals(3, keywords.size());
    }

    @Test
    public void whenGetArticleById_thenReturnArticle() throws Exception {
        mockMvc.perform(
            get("/article/1")
                .contentType(MediaType.APPLICATION_JSON)).
            andExpect(content().json("{\"id\":1,\"header\":\"header\",\"shortDescription\":\"short description\",\"text\":\"text\",\"publishedDate\":\"2019-10-01T21:37:50.000+0000\",\"authors\":[{\"firstName\":\"First Name\",\"lastName\":\"Last Name\"}],\"keywords\":[{\"value\":\"science\"},{\"value\":\"sports\"},{\"value\":\"tech\"}]}"));
    }

    @Test
    public void whenNoArticleFound_thenThrowArticleNotFoundException() throws Exception {
        mockMvc.perform(
            get("/article/2")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}

package com.articles.controllers;

import com.articles.domain.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PostArticlesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private
    ObjectMapper mapper;

    private final String jsonPayloadNewArticle = "{\"id\":1,\"header\":\"header\",\"shortDescription\":\"short description\",\"text\":\"text\",\"publishedDate\":\"2019-10-01T21:37:50.000+0000\",\"authors\":[{\"firstName\":\"First Name\",\"lastName\":\"Last Name\"}],\"keywords\":[{\"value\":\"science\"},{\"value\":\"sports\"},{\"value\":\"tech\"}],\"hibernateLazyInitializer\":{}}";

    @Test
    public void whenInsertingNewArticle_thenSaved() throws Exception {
        mockMvc.perform(post("/article")
            .content(jsonPayloadNewArticle)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void whenInsertingNewArticle_thenReturnArticleHeader() throws Exception {
        MvcResult result = mockMvc.perform(post("/article")
            .content(jsonPayloadNewArticle)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Article article = mapper.readValue(content, Article.class);
        assertEquals("header", article.getHeader());
    }

    @Test
    public void whenInsertingNewArticle_thenReturnArticleText() throws Exception {
        MvcResult result = mockMvc.perform(post("/article")
            .content(jsonPayloadNewArticle)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Article article = mapper.readValue(content, Article.class);
        assertEquals("text", article.getText());
    }

    @Test
    public void whenInsertingNewArticle_thenReturnArticleShortDescription() throws Exception {
        MvcResult result = mockMvc.perform(post("/article")
            .content(jsonPayloadNewArticle)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Article article = mapper.readValue(content, Article.class);
        assertEquals("short description", article.getShortDescription());
    }

    @Test
    public void whenInsertingNewArticle_thenReturnArticleAuthors() throws Exception {
        MvcResult result = mockMvc.perform(post("/article")
            .content(jsonPayloadNewArticle)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Article article = mapper.readValue(content, Article.class);
        assertEquals(1, article.getAuthors().size());
    }

    @Test
    public void whenInsertingNewArticle_thenReturnArticleKeywords() throws Exception {
        MvcResult result = mockMvc.perform(post("/article")
            .content(jsonPayloadNewArticle)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Article article = mapper.readValue(content, Article.class);
        assertEquals(3, article.getKeywords().size());
    }

}

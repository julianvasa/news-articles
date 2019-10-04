package com.articles.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class DeleteArticlesTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenArticleDeleted_thenReturnStatusAccepted() throws Exception {
        mockMvc.perform(delete("/article/{id}", 1))
            .andExpect(status().isAccepted());
    }

    @Test
    public void whenArticleToDeleteDoesNotExist_thenReturnStatusNotFound() throws Exception {
        mockMvc.perform(delete("/article/{id}", 2))
            .andExpect(status().isNotFound());
    }
}

package me.kmsung.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kmsung.springbootdeveloper.domain.Article;
import me.kmsung.springbootdeveloper.dto.AddArticleRequest;
import me.kmsung.springbootdeveloper.repository.BlogRepository;
import me.kmsung.springbootdeveloper.service.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class BlogApiControllerNotMockTest {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    BlogService blogService;

    @AfterEach
    public void tearDown(){
        blogRepository.deleteAllInBatch();
    }

    @DisplayName("블로그에 모든 글을 조회한다.")
    @Test
    void findAllWithArticles(){
        //given
        Article article1 = createArticleEntity("제목 1", "내용 1");
        Article article2 = createArticleEntity("제목 2", "내용 2");
        Article article3 = createArticleEntity("제목 3", "내용 3");
        blogRepository.saveAll(List.of(article1, article2, article3));

        //when
        List<Article> articles = blogService.findAll();

        //then
        assertThat(articles).hasSize(3)
            .extracting("title","content")
            .containsExactlyInAnyOrder(
                tuple("제목 1","내용 1"),
                tuple("제목 2","내용 2"),
                tuple("제목 3","내용 3")
            );
    }

    private Article createArticleEntity(String title, String content) {
        return Article.builder()
            .title(title)
            .content(content)
            .build();
    }

}
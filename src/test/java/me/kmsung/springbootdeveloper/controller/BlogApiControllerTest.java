package me.kmsung.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kmsung.springbootdeveloper.domain.Article;
import me.kmsung.springbootdeveloper.dto.AddArticleRequest;
import me.kmsung.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAllInBatch();
    }

    @DisplayName("addArticle: 블로그 글에 추가에 성공한다.")
    @Test
    void addArticle() throws Exception{
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        ResultActions result = mockMvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isOne();
        assertThat(articles.get(0).getContent()).isEqualTo(content);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
    }

    @DisplayName("모든 블로그의 글을 조회한다.")
    @Test
    void findAll() throws Exception {
        //given
        final String url = "/api/articles";
        Article articleEntity1 = createArticleEntity("제목 1", "내용 1");
        Article articleEntity2 = createArticleEntity("제목 2", "내용 2");
        Article articleEntity3 = createArticleEntity("제목 3", "내용 3");

        blogRepository.saveAll(List.of(articleEntity1, articleEntity2, articleEntity3));

        //when
        ResultActions perform = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isOk())
            .andExpectAll(
                jsonPath("$[0].title").value("제목 1"),
                jsonPath("$[0].content").value("내용 1"),
                jsonPath("$[1].title").value("제목 2"),
                jsonPath("$[1].content").value("내용 2"),
                jsonPath("$[2].title").value("제목 3"),
                jsonPath("$[2].content").value("내용 3")
            );
    }

    private Article createArticleEntity(String title, String content) {
        return Article.builder()
            .title(title)
            .content(content)
            .build();
    }

}
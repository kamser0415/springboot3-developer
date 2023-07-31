package me.kmsung.springbootdeveloper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kmsung.springbootdeveloper.dto.AddArticleRequest;
import me.kmsung.springbootdeveloper.service.BlogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {BlogApiController.class})
public class BlogApiControllerValidateTest {

    @MockBean
    private BlogService blogService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("블로그를 등록할때 제목은 필수 값이다.")
    @Test
    void createBlogWithOutTitle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "";
        final String content = "content";
        AddArticleRequest request = new AddArticleRequest(title, content);
        String requestJson = objectMapper.writeValueAsString(request);

        //when
        ResultActions perform = mockMvc.perform(post(url)
            .content(requestJson)
            .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isBadRequest());
    }
    @DisplayName("블로그를 등록할때 내용은 필수 값이다.")
    @Test
    void createBlogWithOutContent() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "";
        AddArticleRequest request = new AddArticleRequest(title, content);
        String requestJson = objectMapper.writeValueAsString(request);

        //when
        ResultActions perform = mockMvc.perform(post(url)
            .content(requestJson)
            .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isBadRequest());
    }
}

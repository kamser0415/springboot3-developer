package me.kmsung.springbootdeveloper.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kmsung.springbootdeveloper.domain.Article;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddArticleRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Article toEntity(){
        return Article.builder()
            .title(title)
            .content(content)
            .build();
    }

    public AddArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

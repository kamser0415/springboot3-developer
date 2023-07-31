package me.kmsung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kmsung.springbootdeveloper.domain.Article;
import me.kmsung.springbootdeveloper.dto.AddArticleRequest;
import me.kmsung.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

}

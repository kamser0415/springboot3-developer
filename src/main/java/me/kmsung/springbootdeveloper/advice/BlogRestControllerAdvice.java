package me.kmsung.springbootdeveloper.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class BlogRestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult test(MethodArgumentNotValidException e) {
        return new ErrorResult("BAD", e.getMessage());
    }

    @Data
    @AllArgsConstructor
    private static class ErrorResult {
        private String code;
        private String message;
    }
}

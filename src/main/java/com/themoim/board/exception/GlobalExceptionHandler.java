package com.themoim.board.exception;

import com.themoim.board.domain.common.ApiResponseTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseTemplate methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ApiResponseTemplate.OK(ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}

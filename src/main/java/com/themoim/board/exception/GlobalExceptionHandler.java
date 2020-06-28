package com.themoim.board.exception;

import com.themoim.board.common.ApiResponseTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseTemplate methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ApiResponseTemplate.ok(ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(BusinessErrorException.class)
    public ApiResponseTemplate businessErrorException(BusinessErrorException ex) {
        return ApiResponseTemplate.ok(ex.getMessage());
    }
}

package com.themoim.board.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseTemplate {
    private int code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;

    @Builder
    public ApiResponseTemplate(HttpStatus httpStatus, String message, Object data, String accessToken) {
        this.code = httpStatus.value();
        this.message = message;
        this.data = data;
        this.accessToken = accessToken;
    }

    public static ApiResponseTemplate OK(String message){
        return ApiResponseTemplate.builder()
                .httpStatus(HttpStatus.OK)
                .message(message)
                .build();
    }

    public static ApiResponseTemplate OK(String message, String accessToken){
        return ApiResponseTemplate.builder()
                .httpStatus(HttpStatus.OK)
                .message(message)
                .accessToken(accessToken)
                .build();
    }

    public static ApiResponseTemplate OK(Object data){
        return ApiResponseTemplate.builder()
                .httpStatus(HttpStatus.OK)
                .message("OK")
                .data(data)
                .build();
    }

    public static ApiResponseTemplate OK(String message, Object data){
        return ApiResponseTemplate.builder()
                .httpStatus(HttpStatus.OK)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponseTemplate OK() {
        return ApiResponseTemplate.builder().httpStatus(HttpStatus.OK).message("OK").build();
    }
}

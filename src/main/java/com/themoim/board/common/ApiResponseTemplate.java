package com.themoim.board.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseTemplate<T> {
    private int code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;

    public static <T> ApiResponseTemplate<T> ok(T data){
        return (ApiResponseTemplate<T>)ApiResponseTemplate.builder()
                .code(HttpStatus.OK.value())
                .message("OK")
                .data(data)
                .build();
    }
}

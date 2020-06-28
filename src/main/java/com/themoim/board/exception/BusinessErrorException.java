package com.themoim.board.exception;

import lombok.Getter;

@Getter
public class BusinessErrorException extends RuntimeException {
    private String message;


    public BusinessErrorException(String message) {
        this.message = message;
    }
}

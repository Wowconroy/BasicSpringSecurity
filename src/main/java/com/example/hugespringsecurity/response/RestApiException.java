package com.example.hugespringsecurity.response;

public class RestApiException extends RuntimeException{
    public RestApiException(String message) {
        super(message);
    }
}

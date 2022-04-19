package com.example.hugespringsecurity.response;

public class RestApiResponse {
    private int status;
    private String message;
    private Object data;

    public RestApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}

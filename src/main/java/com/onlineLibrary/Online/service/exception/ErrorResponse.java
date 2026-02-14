package com.onlineLibrary.Online.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String type;
    private LocalDateTime time;
    // this field will tell user about the mistakes that he did when he made the request
    private Map<String, String> errors;

    // here I'm adding this custom constructor to return only
    // --> statusCode, message, type, and time
    public ErrorResponse(int statusCode, String message, String type) {
        this.statusCode = statusCode;
        this.message = message;
        this.type = type;
        this.time = LocalDateTime.now();
    }


    public ErrorResponse(int statusCode, String message, String type, Map<String, String> errors) {
        this(statusCode, message, type);
        this.errors = errors;
    }
}

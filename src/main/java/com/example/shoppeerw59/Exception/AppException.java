package com.example.shoppeerw59.Exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
public class AppException extends RuntimeException{
    private Instant timestamp;

    private String message;

    private int code;

    private String path;

    public AppException(ErrorResponseBase errorResponseBase){
        this.code = errorResponseBase.getStatus().value();
        this.message = errorResponseBase.getMessage();
        this.timestamp = Instant.now();
    }

    public AppException(Exception e) {
        this.code = 500;
        this.message = e.getMessage();
        this.timestamp = Instant.now();
    }

    public AppException(String message, int code, String path) {
        this.message = message;
        this.code = code;
        this.path = path;
        this.timestamp = Instant.now();
    }
}

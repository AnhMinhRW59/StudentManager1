package com.example.shoppeerw59.Exception;

import org.springframework.http.HttpStatus;

public enum ErrorResponseBase {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Đối tượng không tồn tại"),
    USERNAME_EXISTED(HttpStatus.INTERNAL_SERVER_ERROR, "Đối tượng đã tồn tại"),
    MIN_MAXINVALID(HttpStatus.BAD_REQUEST, "Số min phải nhỏ hơn số max"),
    LOGIN_FAILS(HttpStatus.UNAUTHORIZED, "Đăng nhập bị từ chối"),
    LOGIN_FAILS_USERNAME(HttpStatus.UNAUTHORIZED, "Sai username rồi"),
    LOGIN_FAILS_PASSWORD(HttpStatus.UNAUTHORIZED, "Sai password rồi")

            ;

    public final HttpStatus status;
    public final String message;

    ErrorResponseBase(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

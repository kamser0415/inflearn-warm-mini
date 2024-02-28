package org.example.miniinflearn.api;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T>{
    private T body;
    private final int code;
    private String message;

    @Builder
    protected ApiResponse(HttpStatus status, T body, String message) {
        this.body = body;
        this.code = status.value();
        this.message = message;
    }

    public static <T> ApiResponse<T> ok(T t) {
        return ApiResponse.<T>builder()
                .status(HttpStatus.OK)
                .body(t)
                .build();
    }

    public static <T> ApiResponse<T> of(HttpStatus status, T body, String message) {
        return ApiResponse.<T>builder()
                .status(status)
                .body(body)
                .message(message)
                .build();
    }
}

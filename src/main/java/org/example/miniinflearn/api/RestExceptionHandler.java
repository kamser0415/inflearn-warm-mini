package org.example.miniinflearn.api;

import org.example.miniinflearn.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> errorHandlerByValid(BindException e) {
        return ApiResponse.of(HttpStatus.BAD_REQUEST, "FAIL",
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}

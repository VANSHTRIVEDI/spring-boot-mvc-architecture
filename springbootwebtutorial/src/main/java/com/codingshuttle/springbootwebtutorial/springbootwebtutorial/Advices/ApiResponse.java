package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ApiResponse<T> {

    private  T data;

    @JsonFormat(pattern = "hh-mm-ss dd-mm-yyyy")
    private LocalDateTime timestamp;
    private ApiError error;


    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {

        this.error = error;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }


}

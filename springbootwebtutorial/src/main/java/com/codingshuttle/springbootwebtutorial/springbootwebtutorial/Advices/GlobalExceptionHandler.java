package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Advices;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice  //this takes control of every controller
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> ResourceNotFound(ResourceNotFoundException exception)
    {
        ApiError error=ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND) ;
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationException(MethodArgumentNotValidException exception)
    {
        List<String>listOfError=exception.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());

        ApiError error=ApiError.builder()
                .status(HttpStatus.BAD_REQUEST).subErrors(listOfError)
                .message("Input validation Error")
                .build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception)
    {
        ApiError error=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR) ;
    }






}

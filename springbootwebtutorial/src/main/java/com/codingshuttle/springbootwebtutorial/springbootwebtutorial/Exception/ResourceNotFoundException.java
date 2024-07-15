package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Exception;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

package com.example.java_project.exception;

public abstract class AbstractException extends RuntimeException {

    AbstractException(String message){
        super(message);
    }
}

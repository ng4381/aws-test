package com.example.awstest.exception;

public class StageNotFoundException extends RuntimeException{
    public StageNotFoundException(String message) {
        super(message);
    }
}

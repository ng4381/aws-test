package com.example.awstest.exception;

public class AssemblyOrderNotFoundException extends RuntimeException{
    public AssemblyOrderNotFoundException(String message) {
        super(message);
    }
}

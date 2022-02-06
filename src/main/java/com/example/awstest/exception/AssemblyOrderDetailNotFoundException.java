package com.example.awstest.exception;

public class AssemblyOrderDetailNotFoundException extends RuntimeException{
    public AssemblyOrderDetailNotFoundException(String message) {
        super(message);
    }
}

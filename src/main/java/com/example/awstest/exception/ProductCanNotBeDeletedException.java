package com.example.awstest.exception;

public class ProductCanNotBeDeletedException extends RuntimeException{
    public ProductCanNotBeDeletedException(String message) {
        super(message);
    }
}

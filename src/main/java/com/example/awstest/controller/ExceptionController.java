package com.example.awstest.controller;

import com.example.awstest.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = AssemblyOrderNotFoundException.class)
    public ResponseEntity<AssemblyOrderNotFoundException> assemblyOrderNotFoundException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AssemblyOrderDetailNotFoundException.class)
    public ResponseEntity<AssemblyOrderDetailNotFoundException> assemblyOrderDetailNotFoundException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AssemblyOrderDetailStageNotFoundException.class)
    public ResponseEntity<AssemblyOrderDetailStageNotFoundException> assemblyOrderDetailStageNotFoundException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundException> productNotFoundException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StageNotFoundException.class)
    public ResponseEntity<StageNotFoundException> stageNotFoundException() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

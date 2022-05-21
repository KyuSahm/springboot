package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
@RestControllerAdvice(basePackages = "com.example.exception.controller")
public class GlobalControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> processMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("*********Global Controller Advice for MethodArgumentNotValidException *******");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 형식의 인자값이 존재합니다.\n" + e.getLocalizedMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> processException(Exception e) {
        System.out.println("*********Global Controller Advice for Exception *******");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부의 에러가 발생하였습니다.");
    }
}

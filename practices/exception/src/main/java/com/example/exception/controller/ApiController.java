package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/user")
    public User get(@RequestParam(required = true) String name,
                    @RequestParam(required = false) Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        // crate NullPointerException
        int newAge = age + 10;

        return user;
    }

    @PostMapping("/user")
    public User post(@Valid @RequestBody User user) {
        System.out.println(user);
        return user;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> processMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("*********Local Controller Advice for ApiController*******");
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 형식의 인자값이 존재합니다.\n" + e.getLocalizedMessage());
    }
}

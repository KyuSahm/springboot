package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user")
    public ResponseEntity<User> user(@Valid @RequestBody User user) {
        System.out.println(user);

// manual check
//        if (user.getAge() > 50) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/user2")
    public ResponseEntity<Object> user(@Valid @RequestBody User user, BindingResult bindingResult) {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = error.getDefaultMessage();
                System.out.printf("Validation error - field: %s, message: %s\n", fieldError.getField(), message);
                sb.append("Validation error - field: ").append(fieldError.getField());
                sb.append(", message: ").append(message).append("\n");
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        return ResponseEntity.ok(user);
    }
}

package com.example.server.controller;

import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {
//    @GetMapping("/hello")
//    public String hello() {
//        return "hello server";
//    }

    @GetMapping("/hello")
    public ResponseEntity<User> hello(@RequestParam String name, @RequestParam int age) {
        User user = new User(name, age);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public ResponseEntity<User> post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName) {
        log.debug("Path Variables userId: {}, userName: {}", userId, userName);
        log.debug("Request Body: {}", user);
        User newUser = new User(user.getName(), user.getAge());
        return ResponseEntity.ok().body(newUser);
    }
}

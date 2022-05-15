package com.example.aop.controller;

import com.example.aop.annotation.Decoder;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        System.out.printf("get method id: %s, name: %s\n", id, name);
        return id + "/" + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        System.out.println("post method: " + user);
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() {
        try {
            // db logic. It takes 2 seconds.
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Decoder
    @PutMapping("/put")
    public User put(@RequestBody User user) {
        System.out.println("put method: " + user);
        return user;
    }
}

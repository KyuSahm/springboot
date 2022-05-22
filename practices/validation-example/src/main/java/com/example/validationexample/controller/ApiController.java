package com.example.validationexample.controller;

import com.example.validationexample.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/user")
    public User get(@Size(min = 2, max = 50) String name,
                    @NotNull @Min(1) @Max(200) Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

        Integer newAge = null;
        newAge += newAge;
        return user;
    }

    @PostMapping("/user")
    public User post(@Valid @RequestBody User user) {
        System.out.println(user);
        return user;
    }
}

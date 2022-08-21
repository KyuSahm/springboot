package com.example.swagger.controller;

import com.example.swagger.dto.UserRequest;
import com.example.swagger.dto.UserResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Sample APIs"})
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y값", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/plus/{x}")
    public int plus(
            //@ApiParam(value = "x값", defaultValue = "20")
            @PathVariable int x,
            //@ApiParam(value = "y값", defaultValue = "5")
            @RequestParam int y) {
        return x + y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일 때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드")
    @GetMapping("/user")
    public UserResponse getUser(UserRequest userRequest) {
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }

    @PostMapping("/user")
    public UserResponse postUser(@RequestBody UserRequest userRequest) {
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }
}

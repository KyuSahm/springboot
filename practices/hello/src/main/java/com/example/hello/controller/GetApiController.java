package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
    @GetMapping(path = "/hello")  // http://localhost:9090/api/get/hello
    public String hello() {
        return "hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET) // http://localhost:9090/api/get/hi
    public String hi() {
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable/{name}
    @GetMapping(path = "/path-variable/{name}")
    public String pathVariable(@PathVariable String name) {
        System.out.println("PathVariable : " + name);
        return name;
    }

    // http://localhost:9090/api/get/path-variable2/{name}
    @GetMapping(path = "/path-variable2/{id}")
    public String pathVariable2(@PathVariable(name = "id") String pathName) {
        System.out.println("PathVariable2 : " + pathName);
        return pathName;
    }

    // http://localhost:90900/api/get/query-params?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path = "/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParams) {
        StringBuilder sb = new StringBuilder();
        queryParams.entrySet().forEach(entry -> {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            sb.append(entry.getKey() + "=" + entry.getValue() + "\n");
        });
        return sb.toString();
    }

    // http://localhost:90900/api/get/query-params?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path = "/query-param02")
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam int age) {
        return name + "," + email + "," + age;
    }

    // http://localhost:90900/api/get/query-params?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path = "/query-param03")
    public String queryParam03(UserRequest userRequest) {
        return userRequest.toString();
    }
}

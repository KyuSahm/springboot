package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiController {
    private final RestTemplateService restTemplateService;

    @GetMapping("/hello")
    public ResponseEntity<UserResponse> hello() {
        return restTemplateService.hello();
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> user() {
        return restTemplateService.user();
    }

    @PostMapping("/exchange")
    public ResponseEntity<UserResponse> exchange() {
        return restTemplateService.exchange();
    }

    @PostMapping("/exchangeOnTemplate")
    public ResponseEntity<Req<UserResponse>> exchangeOnTemplate() {
        return restTemplateService.exchangeOnTemplate();
    }
}

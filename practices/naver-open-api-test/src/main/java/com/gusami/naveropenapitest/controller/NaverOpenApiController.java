package com.gusami.naveropenapitest.controller;

import com.gusami.naveropenapitest.dto.NaverRegionResponse;
import com.gusami.naveropenapitest.service.NaverOpenAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NaverOpenApiController {
    private final NaverOpenAPIService naverOpenAPIService;

    @GetMapping("/naver/search")
    public ResponseEntity<NaverRegionResponse> searchOnNaver(@RequestParam String keyword) {
        return naverOpenAPIService.searchOnNaver(keyword);
    }
}

package com.gusami.naveropenapitest.service;

import com.gusami.naveropenapitest.dto.NaverRegionResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class NaverOpenAPIService {
    private static final String NaverClientId = "iRxZPRYmUEx5TIjkpF7O";
    private static final String NaverClientSecret = "AhMSrnHEwE";

    // https://openapi.naver.com/v1/search/local.json
    // ?query=%EC%9C%A8%EB%8F%99%EA%B3%B5%EC%9B%90
    // &display=5
    // &start=1
    // &sort=random
    public ResponseEntity searchOnNaver(String keyword) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", keyword)
                .queryParam("display", 5)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", NaverClientId)
                .header("X-Naver-Client-Secret", NaverClientSecret)
                .build();

        ResponseEntity<NaverRegionResponse[]> responseEntity = restTemplate.exchange(requestEntity, NaverRegionResponse[].class);
        return responseEntity;
    }
}

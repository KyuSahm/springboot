package com.example.client.service;

import com.example.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    // response
    public ResponseEntity<UserResponse> hello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "steve")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();
        log.debug(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        // return String on Get Method with URI
        //String result = restTemplate.getForObject(uri, String.class);
        //return result;
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        //log.debug("statusCode: {}, Body: {}", responseEntity.getStatusCode(), responseEntity.getBody());
        //return responseEntity.getBody();
        ResponseEntity<UserResponse> responseEntity = restTemplate.getForEntity(uri, UserResponse.class);
        log.debug("statusCode: {}, Body: {}", responseEntity.getStatusCode(), responseEntity.getBody());
        return responseEntity;
    }
}

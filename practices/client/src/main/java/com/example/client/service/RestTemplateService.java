package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    // http://localhost:9090/api/server/hello
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

    // Path Variables example
    // http://localhost:9090/api/server/user/{userId}/name/{userName}
    public ResponseEntity<UserResponse> user() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        log.debug(uri.toString());

        // http body를 보내는 방법
        // RestTemplate에 의해서 아래의 변환이 일어남
        // object -> object mapper -> json string로 변환 후, http body의 json string에 넣어 줌
        UserRequest userRequest = new UserRequest("steve", 10);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> responseEntity = restTemplate.postForEntity(uri, userRequest, UserResponse.class);
        log.debug("statusCode: {}, Header: {}, Body: {}",
                responseEntity.getStatusCode(), responseEntity.getHeaders(), responseEntity.getBody());
        return responseEntity;
    }

    public ResponseEntity<UserResponse> exchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/name/{userName}")
                .encode()
                .build()
                .expand("steve")
                .toUri();
        log.debug(uri.toString());

        // http body를 보내는 방법
        // RestTemplate에 의해서 아래의 변환이 일어남
        // object -> object mapper -> json string로 변환 후, http body의 json string에 넣어 줌
        UserRequest userRequest = new UserRequest("steve", 10);
        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffffff")
                .body(userRequest);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> responseEntity = restTemplate.exchange(requestEntity, UserResponse.class);
        log.debug("statusCode: {}, Header: {}, Body: {}",
                responseEntity.getStatusCode(), responseEntity.getHeaders(), responseEntity.getBody());
        return responseEntity;
    }
}

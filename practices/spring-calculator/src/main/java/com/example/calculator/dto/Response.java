package com.example.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String status = "ok";
    private Body body;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Body {
        private int result;
    }
}

package com.example.springioc.util;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Component
public class Base64Encoder implements IEncoder {

    @Override
    public String encode(String message) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}

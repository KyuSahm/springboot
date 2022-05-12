package com.example.springioc.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

public class Encoder {
    private IEncoder encoder;

    public Encoder(IEncoder encoder) {
        this.encoder = encoder;
    }

    public void setEncoder(IEncoder encoder) {
        this.encoder = encoder;
    }

    public String encode(String message) throws UnsupportedEncodingException {
        return encoder.encode(message);
    }
}

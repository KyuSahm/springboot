package com.company.ioc.util;

import java.io.UnsupportedEncodingException;

public class Encoder {
    private IEncoder encoder;

    public Encoder(IEncoder encoder) {
        this.encoder = encoder;
    }

    public String encode(String message) throws UnsupportedEncodingException {
        return encoder.encode(message);
    }
}

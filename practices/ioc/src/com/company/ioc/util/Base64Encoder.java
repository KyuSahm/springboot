package com.company.ioc.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Encoder implements IEncoder {

    @Override
    public String encode(String message) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}

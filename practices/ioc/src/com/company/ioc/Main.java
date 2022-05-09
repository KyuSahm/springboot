package com.company.ioc;

import com.company.ioc.util.Encoder;
import com.company.ioc.util.UrlEncoder;

import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // Base64 encoding
        try {
            Encoder encoder = new Encoder(new UrlEncoder());
            String urlResult = encoder.encode(url);
            System.out.println(urlResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

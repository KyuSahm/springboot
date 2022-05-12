package com.example.springioc.config;

import com.example.springioc.util.Base64Encoder;
import com.example.springioc.util.Encoder;
import com.example.springioc.util.UrlEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean("base64-encoder")
    public Encoder encoder(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }

    @Bean("url-encoder")
    public Encoder encoder(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }
}

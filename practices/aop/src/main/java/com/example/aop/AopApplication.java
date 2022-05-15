package com.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
		// email 주소의 인코딩 값을 확인		
		System.out.println(Base64.getEncoder().encodeToString(("gusami32@gmail.com".getBytes(StandardCharsets.UTF_8))));
	}

}

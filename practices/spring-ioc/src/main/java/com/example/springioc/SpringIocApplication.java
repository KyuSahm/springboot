package com.example.springioc;

import com.example.springioc.context.ApplicationContextProvider;
import com.example.springioc.util.Base64Encoder;
import com.example.springioc.util.Encoder;
import com.example.springioc.util.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class SpringIocApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SpringIocApplication.class, args);

		// Base64 encoding
		try {
			ApplicationContext context = ApplicationContextProvider.getContext();

			String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
			Encoder encoder = context.getBean("url-encoder", Encoder.class);
			String urlResult = encoder.encode(url);
			System.out.println("encodedUrl: " + urlResult);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}

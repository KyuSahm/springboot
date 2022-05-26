package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Case 01. 모든 Client의 요청에 대해서 Interceptor을 동작시키고 싶을 때
        //registry.addInterceptor(authInterceptor);
        // Case 02. 특정 Client의 요청들에 대해서 Interceptor을 동작시키고 싶을 때. 
        //          addPathPatterns로 필요한 API를 지정
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*", "/api/public/*");
        // 여러 개의 interceptor을 순서대로 등록 가능. 등록한 순서대로 우선 순위를 가짐
        //registry.addInterceptor(xxxInterceptor).addPathPatterns("/api/text/*");
    }
}

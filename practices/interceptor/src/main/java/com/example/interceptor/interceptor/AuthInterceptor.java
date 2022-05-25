package com.example.interceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 만약, Filter에서 아래와 같이 ContentCachingRequestWrapper를 생성해서 doFilter를 호출했다면, 입력인자 request를 ContentCachingRequestWrapper로 Casting 가능
        // ContentCachingRequestWrapper contentCachingRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        // ContentCachingResponseWrapper contentCachingResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);
        // chain.doFilter(contentCachingRequest, contentCachingResponse);
        // ---->
        // ContentCachingRequestWrapper contentCachingRequest = (ContentCachingRequestWrapper)request;
        String uri = request.getRequestURI();
        log.info("request uri: {}", uri);
        
        // 만약, false를 return하면, controller handler method 까지 도달하지 못함
        return false;
    }

    private boolean checkAnnotation(Object handler, Class clazz) {
        // Case 01: handler가 static resources(javascript, html, images 등)에 대한 요청이라면, 무조건 통과
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // Case 02: handler가 일반적인 resources라면, annotation을 체크
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            // Auth annotation이 있을 때는 true
            return true;
        }

        return false;
    }
}

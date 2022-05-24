package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Component
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
//        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
//
//        String uri = httpServletRequest.getRequestURI();
//        BufferedReader bufferedReader = httpServletRequest.getReader();
//        bufferedReader.lines().forEach(line -> {
//            log.info("uri: {}, line: {}", uri, line);
//        });
//        chain.doFilter(request, response);

        ContentCachingRequestWrapper contentCachingRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper contentCachingResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        chain.doFilter(contentCachingRequest, contentCachingResponse);

        String reqContent = new String(contentCachingRequest.getContentAsByteArray());
        log.info("request uri: {}, requestBody: {}", contentCachingRequest.getRequestURI(), reqContent);

        String resContent = new String(contentCachingResponse.getContentAsByteArray());
        int httpStatusCode = contentCachingResponse.getStatus();
        log.info("response status: {}, responseBody: {}", httpStatusCode, resContent);

    }
}

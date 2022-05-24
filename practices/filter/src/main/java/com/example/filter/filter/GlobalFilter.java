package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper contentCachingRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper contentCachingResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        chain.doFilter(contentCachingRequest, contentCachingResponse);

        String reqContent = new String(contentCachingRequest.getContentAsByteArray());
        log.info("request uri: {}, requestBody: {}", contentCachingRequest.getRequestURI(), reqContent);

        String resContent = new String(contentCachingResponse.getContentAsByteArray());
        int httpStatusCode = contentCachingResponse.getStatus();

        // should copy the cached content to response
        contentCachingResponse.copyBodyToResponse();
        log.info("response status: {}, responseBody: {}", httpStatusCode, resContent);

    }
}

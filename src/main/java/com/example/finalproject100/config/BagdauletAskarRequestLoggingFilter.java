package com.example.finalproject100.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class BagdauletAskarRequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        long startTime = System.currentTimeMillis();
        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();

        log.info("Incoming request: {} {}", method, uri);

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        int status = httpResponse.getStatus();

        if (status >= 400) {
            log.error("Request completed: {} {} -> {} ({}ms)", method, uri, status, duration);
        } else {
            log.info("Request completed: {} {} -> {} ({}ms)", method, uri, status, duration);
        }
    }
}

package com.example.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class CsrfHttpHeaderProvider implements HttpHeaderProvider {
    @Override
    public void filter(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "X-CSRF-TOKEN");
    }
}
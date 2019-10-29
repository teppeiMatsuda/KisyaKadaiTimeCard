package com.example.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpHeaderProvider {
    void filter(HttpServletRequest request, HttpServletResponse response);
}
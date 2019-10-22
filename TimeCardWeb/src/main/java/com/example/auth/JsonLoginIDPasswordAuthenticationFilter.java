package com.example.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * JsonLoginIDPasswordAuthenticationFilter
 */
@Setter
@Getter
public class JsonLoginIDPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

    ObjectMapper objectMapper = new ObjectMapper();

    String loginIdParameter = "loginId";
    String passwordParameter = "password";

    public JsonLoginIDPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login","POST"));
        this.setAuthenticationManager(authenticationManager);
    }


    @SuppressWarnings("unchecked")
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Map<String, Object> requestObject;
        try {
            requestObject = objectMapper.readValue(request.getInputStream(), Map.class);
        } catch (IOException e) {
            requestObject = new HashMap<>();
        }

        String loginId =
                Optional
                        .ofNullable(requestObject.get(loginIdParameter))
                        .map(Object::toString)
                        .map(String::trim)
                        .orElse("");
        String password =
                Optional
                        .ofNullable(requestObject.get(passwordParameter))
                        .map(Object::toString)
                        .orElse("");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
        		loginId, password);

        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

        return this.getAuthenticationManager().authenticate(authRequest);
    }


}
package com.touchsoft.timetracker.api.security.configure;


import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("jwtAuthenticationEntryPoint")
@ControllerAdvice
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public void commence(HttpServletResponse response,BadCredentialsException ex) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @ExceptionHandler(value = {SignatureException.class})
    public void commence(HttpServletResponse response,SignatureException ex) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
    }

    @ExceptionHandler(value={UsernameNotFoundException.class})
    public void commence(HttpServletResponse response,UsernameNotFoundException ex) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
    }

}
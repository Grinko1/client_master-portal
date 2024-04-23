package com.clientmaster.app.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("{\"status\": 401, \"error\": \"Forbidden you should login.\"}");
    }

//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        response.setContentType("application/json");
//
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Forbidden you should login.");
//        OutputStream out = response.getOutputStream();
//        objectMapper.writeValue(out, errorResponse);
//        out.flush();
//    }
//
//    private static class ErrorResponse {
//        private final int status;
//        private final String error;
//
//        public ErrorResponse(int status, String error) {
//            this.status = status;
//            this.error = error;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public String getError() {
//            return error;
//        }
//    }

}
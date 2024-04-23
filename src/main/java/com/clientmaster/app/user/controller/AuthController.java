package com.clientmaster.app.user.controller;

import com.clientmaster.app.config.jwt.JwtAuthenticationResponse;
import com.clientmaster.app.config.jwt.JwtSignUpResponse;
import com.clientmaster.app.exceptions.AppError;
import com.clientmaster.app.exceptions.TokenExpiredException;
import com.clientmaster.app.user.dto.SignDto;
import com.clientmaster.app.user.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignDto request) {
        try{
            return  ResponseEntity.ok(authenticationService.signUp(request));
        }catch (RuntimeException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User with this email already exist"), HttpStatus.BAD_REQUEST);
        }

    }


    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignDto request) {
        try {
            JwtAuthenticationResponse response = authenticationService.signIn(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong username or password"), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        System.out.println("refresh");
//        todo refresh
//        service.refreshToken(request, response);
    }
}
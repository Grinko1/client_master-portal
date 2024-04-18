package com.clientmaster.app.user.controller;

import com.clientmaster.app.config.jwt.JwtAuthenticationResponse;
import com.clientmaster.app.config.jwt.JwtSignUpResponse;
import com.clientmaster.app.user.dto.SignDto;
import com.clientmaster.app.user.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtSignUpResponse signUp(@RequestBody @Valid SignDto request) {
        System.out.println("here");
        System.out.println("request: "+ request);
        return authenticationService.signUp(request);
    }


    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignDto request) {
        System.out.println("LOGIN");
        System.out.println(request);
         JwtAuthenticationResponse response = authenticationService.signIn(request);
        System.out.println("response "+response);
        return response;
    }
}
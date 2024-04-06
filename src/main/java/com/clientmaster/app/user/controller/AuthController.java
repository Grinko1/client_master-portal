package com.clientmaster.app.user.controller;

import com.clientmaster.app.config.jwt.JwtAuthenticationResponse;
import com.clientmaster.app.user.dto.SignDto;
import com.clientmaster.app.user.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignDto request) {
        return authenticationService.signUp(request);
    }


    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignDto request) {
        return authenticationService.signIn(request);
    }
}
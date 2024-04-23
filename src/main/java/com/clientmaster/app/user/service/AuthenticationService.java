package com.clientmaster.app.user.service;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.client.entity.Client;
import com.clientmaster.app.client.service.ClientService;
import com.clientmaster.app.config.jwt.JwtAuthenticationResponse;
import com.clientmaster.app.config.jwt.JwtService;
import com.clientmaster.app.config.jwt.JwtSignUpResponse;
import com.clientmaster.app.exceptions.AppError;
import com.clientmaster.app.master.dto.MasterInfoDto;
import com.clientmaster.app.master.entity.Master;
import com.clientmaster.app.master.service.MasterService;
import com.clientmaster.app.user.dto.SignDto;
import com.clientmaster.app.user.entity.Profile;
import com.clientmaster.app.user.entity.Role;
import com.clientmaster.app.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final ClientService clientService;
    private final MasterService masterService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtSignUpResponse signUp(SignDto request) {
        System.out.println(request);
        try{
            var user = User.builder()
                    .username(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            User userData = userService.create(user);

            var jwt = jwtService.generateToken(user);
            return new JwtSignUpResponse(jwt, user.getId());
        }catch (RuntimeException e){
            throw e;
        }

    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignDto request) {
        System.out.println("sign in");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Wrong email or password");
        }


        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());
        User userData = userService.getByUsername(request.getEmail());


        Profile profile = null; // Initialize to null
        if (userData != null) {
            if (userData.getRole() == Role.CLIENT_ROLE) {
                Client clientInfo = clientService.findByUserId(userData.getId());
                if (clientInfo != null) {
                    profile = modelMapper.map(clientInfo, ClientInfoDto.class);
                }
            } else if (userData.getRole() == Role.MASTER_ROLE) {
                Master masterInfo = masterService.findByUserId(userData.getId());
                if (masterInfo != null) {
                    profile = modelMapper.map(masterInfo, MasterInfoDto.class);
                }
            }
        }
        System.out.println("profile " + profile);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, profile, userData.getRole(), userData.getId());
    }
}
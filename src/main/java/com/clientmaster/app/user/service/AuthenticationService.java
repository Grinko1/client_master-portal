package com.clientmaster.app.user.service;

import com.clientmaster.app.client.dto.ClientInfoDto;
import com.clientmaster.app.client.entity.Client;
import com.clientmaster.app.client.service.ClientService;
import com.clientmaster.app.config.jwt.JwtAuthenticationResponse;
import com.clientmaster.app.config.jwt.JwtService;
import com.clientmaster.app.config.jwt.JwtSignUpResponse;
import com.clientmaster.app.master.dto.MasterInfoDto;
import com.clientmaster.app.master.entity.Master;
import com.clientmaster.app.master.service.MasterService;
import com.clientmaster.app.user.dto.SignDto;
import com.clientmaster.app.user.entity.Profile;
import com.clientmaster.app.user.entity.Role;
import com.clientmaster.app.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
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
        var user = User.builder()
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        System.out.println(user);
        User userData = userService.create(user);

        var jwt = jwtService.generateToken(user);
//        var id = userData.getId();
//        Profile profile;
//        if ( userData.getRole() == Role.CLIENT_ROLE) {
//            profile = modelMapper.map(clientService.findByUserId(userData.getId()), ClientInfoDto.class);
//
//
//        } else if (userData.getRole() == Role.MASTER_ROLE) {
//            profile = modelMapper.map(masterService.findByUserId(userData.getId()), MasterInfoDto.class);
//        } else {
//            profile = null;
//        }
//        System.out.println("profile " + profile);
        return new JwtSignUpResponse(jwt, user.getId());
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());
        User userData = userService.getByUsername(request.getEmail());


        System.out.println("userData " + userData.getRole());
        Profile profile = null; // Initialize to null
        if (userData != null) {
            if (userData.getRole() == Role.CLIENT_ROLE) {
                Client clientInfo=  clientService.findByUserId(userData.getId());
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
        return new JwtAuthenticationResponse(jwt,profile, userData.getRole());
    }
}
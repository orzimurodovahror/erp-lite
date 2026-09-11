package com.example.erplite.service.impl;

import com.example.erplite.dto.auth.LoginRequest;
import com.example.erplite.dto.auth.LoginResponse;
import com.example.erplite.security.CustomUserDetails;
import com.example.erplite.security.JwtService;
import com.example.erplite.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            CustomUserDetails userDetails =
                    (CustomUserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            return new LoginResponse(token);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
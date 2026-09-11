package com.example.erplite.controller;

import com.example.erplite.dto.auth.LoginRequest;
import com.example.erplite.dto.auth.LoginResponse;
import com.example.erplite.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        System.out.println("LOGIN API");
        return authService.login(request);
    }
}
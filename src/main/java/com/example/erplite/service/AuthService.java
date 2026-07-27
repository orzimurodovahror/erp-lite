package com.example.erplite.service;

import com.example.erplite.dto.auth.LoginRequest;
import com.example.erplite.dto.auth.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}
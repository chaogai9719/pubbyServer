package com.example.pubbyserver.service;

import com.example.pubbyserver.entity.LoginRequest;
import com.example.pubbyserver.entity.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
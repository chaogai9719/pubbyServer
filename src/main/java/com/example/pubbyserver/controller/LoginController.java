package com.example.pubbyserver.controller;

import com.example.pubbyserver.annotation.OperationLog;
import com.example.pubbyserver.entity.LoginRequest;
import com.example.pubbyserver.entity.LoginResponse;
import com.example.pubbyserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthService authService;

    @OperationLog(module = "鉴权管理", type = "LOGIN")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).build(); // 未授权
        }
    }

    @OperationLog(module = "鉴权管理", type = "LOGOUT")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}
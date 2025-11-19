package com.example.pubbyserver.entity;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private User user;
}
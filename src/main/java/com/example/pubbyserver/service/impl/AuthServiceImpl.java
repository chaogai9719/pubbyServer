package com.example.pubbyserver.service.impl;

import com.example.pubbyserver.dao.UserDao;
import com.example.pubbyserver.entity.LoginRequest;
import com.example.pubbyserver.entity.LoginResponse;
import com.example.pubbyserver.entity.User;
import com.example.pubbyserver.service.AuthService;
import com.example.pubbyserver.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userDao.getUserByUsername(loginRequest.getUsername());
        
        // 使用PasswordEncoder验证密码
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // 生成token
            String token = jwtUtil.generateToken(user.getUsername());
            
            // 创建响应对象
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            user.setPassword(null);
            response.setUser(user);
            
            return response;
        }
        
        return null;
    }
}
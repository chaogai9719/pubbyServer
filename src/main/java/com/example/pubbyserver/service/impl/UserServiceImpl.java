package com.example.pubbyserver.service.impl;

import com.example.pubbyserver.dao.UserDao;
import com.example.pubbyserver.entity.User;
import com.example.pubbyserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    @Override
    public User createUser(User user) {
        // 对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.insertUser(user);
        return user;
    }
    
    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userDao.getUserById(id);
        if (existingUser != null) {
            // 更新用户信息
            existingUser.setUsername(user.getUsername());
            // 如果密码发生了变化，则加密新密码
            if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setNickname(user.getNickname());
            existingUser.setAvatar(user.getAvatar());
            existingUser.setStatus(user.getStatus());
            
            userDao.updateUser(existingUser);
            return existingUser;
        }
        return null;
    }
    
    @Override
    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id) > 0;
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
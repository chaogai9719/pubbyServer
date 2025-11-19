package com.example.pubbyserver.service;

import com.example.pubbyserver.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
    User getUserByUsername(String username);
}
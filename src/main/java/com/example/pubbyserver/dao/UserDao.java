package com.example.pubbyserver.dao;

import com.example.pubbyserver.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    User getUserById(Long id);

    List<User> getAllUsers();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);

    User getUserByUsername(String username);
}
package com.example.pubbyserver.entity;

import lombok.Data;

@Data
public class User extends BaseEntity{
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String nickname;
    private String avatar;
    private Integer status;
}
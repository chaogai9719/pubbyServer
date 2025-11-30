package com.example.pubbyserver.entity;

import lombok.Data;

@Data
public class Dog extends BaseEntity {
    private Long id;
    private String dogImage;
    private String description;
}
package com.example.pubbyserver.entity;

import lombok.Data;

@Data
public class FoodReview extends BaseEntity {
    private Long id;
    private String restaurantName;
    private String restaurantLocation;
    private Integer rating;
    private String foodImage;
    private String remark;
}
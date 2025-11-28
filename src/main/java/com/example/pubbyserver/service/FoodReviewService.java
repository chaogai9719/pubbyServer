package com.example.pubbyserver.service;

import com.example.pubbyserver.entity.FoodReview;

import java.util.List;

public interface FoodReviewService {
    /**
     * 根据ID获取美食点评详情
     * @param id 点评ID
     * @return 美食点评信息
     */
    FoodReview getFoodReviewById(Long id);

    /**
     * 获取美食点评列表（支持按评分筛选）
     * @param rating 评分（可选）
     * @return 美食点评列表
     */
    List<FoodReview> getFoodReviewList(Integer rating);

    /**
     * 获取所有美食点评
     * @return 美食点评列表
     */
    List<FoodReview> getAllFoodReviews();

    /**
     * 创建新的美食点评
     * @param foodReview 美食点评信息
     * @return 是否成功
     */
    boolean createFoodReview(FoodReview foodReview);

    /**
     * 更新美食点评信息
     * @param foodReview 美食点评信息
     * @return 是否成功
     */
    boolean updateFoodReview(FoodReview foodReview);

    /**
     * 删除美食点评
     * @param id 点评ID
     * @return 是否成功
     */
    boolean deleteFoodReview(Long id);
}
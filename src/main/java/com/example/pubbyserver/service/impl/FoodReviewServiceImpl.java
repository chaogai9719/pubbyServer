package com.example.pubbyserver.service.impl;

import com.example.pubbyserver.dao.FoodReviewDao;
import com.example.pubbyserver.entity.FoodReview;
import com.example.pubbyserver.service.FoodReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodReviewServiceImpl implements FoodReviewService {

    @Autowired
    private FoodReviewDao foodReviewDao;

    @Override
    public FoodReview getFoodReviewById(Long id) {
        return foodReviewDao.selectById(id);
    }

    @Override
    public List<FoodReview> getFoodReviewList(Integer rating) {
        if (rating != null) {
            return foodReviewDao.selectListByRating(rating);
        } else {
            return foodReviewDao.selectAll();
        }
    }

    @Override
    public List<FoodReview> getAllFoodReviews() {
        return foodReviewDao.selectAll();
    }

    @Override
    public boolean createFoodReview(FoodReview foodReview) {
        return foodReviewDao.insert(foodReview) > 0;
    }

    @Override
    public boolean updateFoodReview(FoodReview foodReview) {
        return foodReviewDao.update(foodReview) > 0;
    }

    @Override
    public boolean deleteFoodReview(Long id) {
        return foodReviewDao.deleteById(id) > 0;
    }
}
package com.example.pubbyserver.dao;

import com.example.pubbyserver.entity.FoodReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodReviewDao {
    /**
     * 根据ID查询美食点评
     * @param id 点评ID
     * @return 美食点评信息
     */
    FoodReview selectById(Long id);

    /**
     * 根据评分查询美食点评列表
     * @param rating 评分（可选）
     * @return 美食点评列表
     */
    List<FoodReview> selectListByRating(Integer rating);

    /**
     * 查询所有美食点评
     * @return 美食点评列表
     */
    List<FoodReview> selectAll();

    /**
     * 插入新的美食点评
     * @param foodReview 美食点评信息
     * @return 影响行数
     */
    int insert(FoodReview foodReview);

    /**
     * 更新美食点评信息
     * @param foodReview 美食点评信息
     * @return 影响行数
     */
    int update(FoodReview foodReview);

    /**
     * 根据ID删除美食点评
     * @param id 点评ID
     * @return 影响行数
     */
    int deleteById(Long id);
}
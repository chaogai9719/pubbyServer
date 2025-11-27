package com.example.pubbyserver.service;

import com.example.pubbyserver.entity.QuarrelRecord;

import java.util.List;

public interface QuarrelRecordService {

    /**
     * 保存吵架记录
     *
     * @param quarrelRecord 吵架记录实体
     */
    void saveQuarrelRecord(QuarrelRecord quarrelRecord);

    /**
     * 更新吵架记录
     *
     * @param quarrelRecord 吵架记录实体
     */
    void updateQuarrelRecord(QuarrelRecord quarrelRecord);

    /**
     * 根据ID删除吵架记录
     *
     * @param id 吵架记录ID
     */
    void deleteQuarrelRecordById(Long id);

    /**
     * 根据ID获取吵架记录
     *
     * @param id 吵架记录ID
     * @return 吵架记录实体
     */
    QuarrelRecord getQuarrelRecordById(Long id);

    /**
     * 获取所有吵架记录
     *
     * @return 吵架记录列表
     */
    List<QuarrelRecord> getAllQuarrelRecords();

    /**
     * 分页获取吵架记录
     *
     * @param page 页面序号(从0开始)
     * @param size 页面大小
     * @param theme 吵架主题
     * @return 吵架记录列表
     */
    List<QuarrelRecord> getQuarrelRecordsByPage(int page, int size, String theme);

    /**
     * 统计符合条件的吵架记录数量
     *
     * @param theme 吵架主题
     * @return 记录数量
     */
    int countQuarrelRecords(String theme);
}
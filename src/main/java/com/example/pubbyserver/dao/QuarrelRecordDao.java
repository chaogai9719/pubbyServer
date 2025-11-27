package com.example.pubbyserver.dao;

import com.example.pubbyserver.entity.QuarrelRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuarrelRecordDao {

    /**
     * 插入吵架记录
     *
     * @param quarrelRecord 吵架记录实体
     * @return 影响行数
     */
    int insertQuarrelRecord(QuarrelRecord quarrelRecord);

    /**
     * 更新吵架记录
     *
     * @param quarrelRecord 吵架记录实体
     * @return 影响行数
     */
    int updateQuarrelRecord(QuarrelRecord quarrelRecord);

    /**
     * 根据ID删除吵架记录
     *
     * @param id 吵架记录ID
     * @return 影响行数
     */
    int deleteQuarrelRecordById(Long id);

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
     * @param offset 偏移量
     * @param size 页面大小
     * @param theme 吵架主题
     * @return 吵架记录列表
     */
    List<QuarrelRecord> getQuarrelRecordsByPage(@Param("offset") int offset,
                                               @Param("size") int size,
                                               @Param("theme") String theme);

    /**
     * 统计符合条件的吵架记录数量
     *
     * @param theme 吵架主题
     * @return 记录数量
     */
    int countQuarrelRecords(@Param("theme") String theme);
}
package com.example.pubbyserver.dao;

import com.example.pubbyserver.entity.Dog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DogDao {
    /**
     * 根据ID查询小狗记录
     * @param id 小狗记录ID
     * @return 小狗记录信息
     */
    Dog selectById(Long id);

    /**
     * 查询所有小狗记录
     * @return 小狗记录列表
     */
    List<Dog> selectAll();

    /**
     * 插入新的小狗记录
     * @param dog 小狗记录信息
     * @return 影响行数
     */
    int insert(Dog dog);

    /**
     * 更新小狗记录信息
     * @param dog 小狗记录信息
     * @return 影响行数
     */
    int update(Dog dog);

    /**
     * 根据ID删除小狗记录
     * @param id 小狗记录ID
     * @return 影响行数
     */
    int deleteById(Long id);
}
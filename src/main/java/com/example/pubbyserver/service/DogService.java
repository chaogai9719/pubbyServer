package com.example.pubbyserver.service;

import com.example.pubbyserver.entity.Dog;

import java.util.List;

public interface DogService {
    /**
     * 根据ID获取小狗记录详情
     * @param id 记录ID
     * @return 小狗记录信息
     */
    Dog getDogById(Long id);

    /**
     * 获取所有小狗记录
     * @return 小狗记录列表
     */
    List<Dog> getAllDogs();

    /**
     * 创建新的小狗记录
     * @param dog 小狗记录信息
     * @return 是否成功
     */
    boolean createDog(Dog dog);

    /**
     * 更新小狗记录信息
     * @param dog 小狗记录信息
     * @return 是否成功
     */
    boolean updateDog(Dog dog);

    /**
     * 删除小狗记录
     * @param id 记录ID
     * @return 是否成功
     */
    boolean deleteDog(Long id);
}
package com.example.pubbyserver.service;

import com.example.pubbyserver.entity.OperationLogEntity;

import java.util.List;

public interface OperationLogService {

    /**
     * 保存操作日志
     *
     * @param operationLog 操作日志实体
     */
    void saveOperationLog(OperationLogEntity operationLog);

    /**
     * 根据ID获取操作日志
     *
     * @param id 日志ID
     * @return 操作日志实体
     */
    OperationLogEntity getOperationLogById(Long id);

    /**
     * 获取所有操作日志
     *
     * @return 操作日志列表
     */
    List<OperationLogEntity> getAllOperationLogs();

    /**
     * 根据模块名称获取操作日志
     *
     * @param module 模块名称
     * @return 操作日志列表
     */
    List<OperationLogEntity> getOperationLogsByModule(String module);

    /**
     * 根据操作人获取操作日志
     *
     * @param operator 操作人
     * @return 操作日志列表
     */
    List<OperationLogEntity> getOperationLogsByOperator(String operator);

    /**
     * 根据操作类型获取操作日志
     *
     * @param type 操作类型
     * @return 操作日志列表
     */
    List<OperationLogEntity> getOperationLogsByType(String type);
}
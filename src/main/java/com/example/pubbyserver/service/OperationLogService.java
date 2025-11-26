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
     * 获取所有操作日志
     *
     * @return 操作日志列表
     */
    List<OperationLogEntity> getAllOperationLogs();

    /**
     * 分页获取操作日志
     *
     * @param page 页面序号(从0开始)
     * @param size 页面大小
     * @param operationModule 操作模块
     * @param operationType 操作类型
     * @param operator 操作人
     * @return 操作日志列表
     */
    List<OperationLogEntity> getOperationLogsByPage(int page, int size, String operationModule, String operationType, String operator);

    /**
     * 统计符合条件的操作日志数量
     *
     * @param operationModule 操作模块
     * @param operationType 操作类型
     * @param operator 操作人
     * @return 日志数量
     */
    int countOperationLogs(String operationModule, String operationType, String operator);
}
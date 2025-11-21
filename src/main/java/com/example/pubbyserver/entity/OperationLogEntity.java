package com.example.pubbyserver.entity;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志实体类
 */
@Data
public class OperationLogEntity {
    /**
     * 主键
     */
    private Long id;

    /**
     * 操作模块
     */
    private String operationModule;

    /**
     * 操作类型(INSERT/UPDATE/DELETE)
     */
    private String operationType;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 执行时间(ms)
     */
    private Long executionTime;

    /**
     * 状态(1:成功 0:失败)
     */
    private Integer status = 1;
}
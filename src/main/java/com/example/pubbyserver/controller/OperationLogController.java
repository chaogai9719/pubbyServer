package com.example.pubbyserver.controller;

import com.example.pubbyserver.entity.OperationLogEntity;
import com.example.pubbyserver.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 获取所有操作日志
     *
     * @return 操作日志列表
     */
    @GetMapping
    public List<OperationLogEntity> getAllOperationLogs() {
        return operationLogService.getAllOperationLogs();
    }

    /**
     * 根据ID获取操作日志
     *
     * @param id 日志ID
     * @return 操作日志实体
     */
    @GetMapping("/{id}")
    public OperationLogEntity getOperationLogById(@PathVariable Long id) {
        return operationLogService.getOperationLogById(id);
    }

    /**
     * 根据模块名称获取操作日志
     *
     * @param module 模块名称
     * @return 操作日志列表
     */
    @GetMapping("/module/{module}")
    public List<OperationLogEntity> getOperationLogsByModule(@PathVariable String module) {
        return operationLogService.getOperationLogsByModule(module);
    }

    /**
     * 根据操作人获取操作日志
     *
     * @param operator 操作人
     * @return 操作日志列表
     */
    @GetMapping("/operator/{operator}")
    public List<OperationLogEntity> getOperationLogsByOperator(@PathVariable String operator) {
        return operationLogService.getOperationLogsByOperator(operator);
    }

    /**
     * 根据操作类型获取操作日志
     *
     * @param type 操作类型
     * @return 操作日志列表
     */
    @GetMapping("/type/{type}")
    public List<OperationLogEntity> getOperationLogsByType(@PathVariable String type) {
        return operationLogService.getOperationLogsByType(type);
    }
}
package com.example.pubbyserver.controller;

import com.example.pubbyserver.entity.OperationLogEntity;
import com.example.pubbyserver.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 分页获取操作日志，支持条件查询
     *
     * @param page 页面序号(从0开始)
     * @param size 页面大小
     * @param operationModule 操作模块
     * @param operationType 操作类型
     * @param operator 操作人
     * @return 分页结果包含操作日志列表和总数
     */
    @GetMapping("/page")
    public Map<String, Object> getOperationLogsPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String operationModule,
            @RequestParam(required = false) String operationType,
            @RequestParam(required = false) String operator) {
        
        Map<String, Object> result = new HashMap<>();
        List<OperationLogEntity> logs = operationLogService.getOperationLogsByPage(page, size, operationModule, operationType, operator);
        int total = operationLogService.countOperationLogs(operationModule, operationType, operator);
        
        result.put("content", logs);
        result.put("totalElements", total);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        result.put("currentPage", page);
        
        return result;
    }
}
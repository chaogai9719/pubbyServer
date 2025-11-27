package com.example.pubbyserver.controller;

import com.example.pubbyserver.annotation.OperationLog;
import com.example.pubbyserver.entity.QuarrelRecord;
import com.example.pubbyserver.service.QuarrelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quarrels")
public class QuarrelRecordController {

    @Autowired
    private QuarrelRecordService quarrelRecordService;

    /**
     * 创建吵架记录
     *
     * @param quarrelRecord 吵架记录实体
     * @return 吵架记录实体
     */
    @PostMapping
    @OperationLog(module = "吵架记录", type = "INSERT")
    public QuarrelRecord createQuarrelRecord(@RequestBody QuarrelRecord quarrelRecord) {
        quarrelRecordService.saveQuarrelRecord(quarrelRecord);
        return quarrelRecord;
    }

    /**
     * 更新吵架记录
     *
     * @param id 吵架记录ID
     * @param quarrelRecord 吵架记录实体
     * @return 吵架记录实体
     */
    @PutMapping("/{id}")
    @OperationLog(module = "吵架记录", type = "UPDATE")
    public QuarrelRecord updateQuarrelRecord(@PathVariable Long id, @RequestBody QuarrelRecord quarrelRecord) {
        quarrelRecord.setId(id);
        quarrelRecordService.updateQuarrelRecord(quarrelRecord);
        return quarrelRecord;
    }

    /**
     * 删除吵架记录
     *
     * @param id 吵架记录ID
     */
    @DeleteMapping("/{id}")
    @OperationLog(module = "吵架记录", type = "DELETE")
    public void deleteQuarrelRecord(@PathVariable Long id) {
        quarrelRecordService.deleteQuarrelRecordById(id);
    }

    /**
     * 根据ID获取吵架记录
     *
     * @param id 吵架记录ID
     * @return 吵架记录实体
     */
    @GetMapping("/{id}")
    public QuarrelRecord getQuarrelRecord(@PathVariable Long id) {
        return quarrelRecordService.getQuarrelRecordById(id);
    }

    /**
     * 获取所有吵架记录
     *
     * @return 吵架记录列表
     */
    @GetMapping
    public List<QuarrelRecord> getAllQuarrelRecords() {
        return quarrelRecordService.getAllQuarrelRecords();
    }

    /**
     * 分页获取吵架记录，支持条件查询
     *
     * @param page 页面序号(从0开始)
     * @param size 页面大小
     * @param theme 吵架主题
     * @return 分页结果包含吵架记录列表和总数
     */
    @GetMapping("/page")
    public Map<String, Object> getQuarrelRecordsPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String theme) {

        Map<String, Object> result = new HashMap<>();
        List<QuarrelRecord> quarrels = quarrelRecordService.getQuarrelRecordsByPage(page, size, theme);
        int total = quarrelRecordService.countQuarrelRecords(theme);

        result.put("content", quarrels);
        result.put("totalElements", total);
        result.put("totalPages", (int) Math.ceil((double) total / size));
        result.put("currentPage", page);

        return result;
    }
}
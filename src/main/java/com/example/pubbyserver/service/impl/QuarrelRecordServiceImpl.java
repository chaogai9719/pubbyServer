package com.example.pubbyserver.service.impl;

import com.example.pubbyserver.dao.QuarrelRecordDao;
import com.example.pubbyserver.entity.QuarrelRecord;
import com.example.pubbyserver.service.QuarrelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuarrelRecordServiceImpl implements QuarrelRecordService {

    @Autowired
    private QuarrelRecordDao quarrelRecordDao;

    @Override
    public void saveQuarrelRecord(QuarrelRecord quarrelRecord) {
        quarrelRecordDao.insertQuarrelRecord(quarrelRecord);
    }

    @Override
    public void updateQuarrelRecord(QuarrelRecord quarrelRecord) {
        quarrelRecordDao.updateQuarrelRecord(quarrelRecord);
    }

    @Override
    public void deleteQuarrelRecordById(Long id) {
        quarrelRecordDao.deleteQuarrelRecordById(id);
    }

    @Override
    public QuarrelRecord getQuarrelRecordById(Long id) {
        return quarrelRecordDao.getQuarrelRecordById(id);
    }

    @Override
    public List<QuarrelRecord> getAllQuarrelRecords() {
        return quarrelRecordDao.getAllQuarrelRecords();
    }

    @Override
    public List<QuarrelRecord> getQuarrelRecordsByPage(int page, int size, String theme) {
        int offset = page * size;
        return quarrelRecordDao.getQuarrelRecordsByPage(offset, size, theme);
    }

    @Override
    public int countQuarrelRecords(String theme) {
        return quarrelRecordDao.countQuarrelRecords(theme);
    }
}
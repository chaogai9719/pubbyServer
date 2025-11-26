package com.example.pubbyserver.service.impl;

import com.example.pubbyserver.dao.OperationLogDao;
import com.example.pubbyserver.entity.OperationLogEntity;
import com.example.pubbyserver.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogDao operationLogDao;

    @Override
    public void saveOperationLog(OperationLogEntity operationLog) {
        operationLogDao.insertOperationLog(operationLog);
    }

    @Override
    public List<OperationLogEntity> getAllOperationLogs() {
        return operationLogDao.getAllOperationLogs();
    }

    @Override
    public List<OperationLogEntity> getOperationLogsByPage(int page, int size, String operationModule, String operationType, String operator) {
        int offset = page * size;
        return operationLogDao.getOperationLogsByPage(offset, size, operationModule, operationType, operator);
    }

    @Override
    public int countOperationLogs(String operationModule, String operationType, String operator) {
        return operationLogDao.countOperationLogs(operationModule, operationType, operator);
    }
}
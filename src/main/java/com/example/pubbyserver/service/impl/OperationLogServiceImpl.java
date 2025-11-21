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
    public OperationLogEntity getOperationLogById(Long id) {
        return operationLogDao.getOperationLogById(id);
    }

    @Override
    public List<OperationLogEntity> getAllOperationLogs() {
        return operationLogDao.getAllOperationLogs();
    }

    @Override
    public List<OperationLogEntity> getOperationLogsByModule(String module) {
        return operationLogDao.getOperationLogsByModule(module);
    }

    @Override
    public List<OperationLogEntity> getOperationLogsByOperator(String operator) {
        return operationLogDao.getOperationLogsByOperator(operator);
    }

    @Override
    public List<OperationLogEntity> getOperationLogsByType(String type) {
        return operationLogDao.getOperationLogsByType(type);
    }
}
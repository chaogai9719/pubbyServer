package com.example.pubbyserver.dao;

import com.example.pubbyserver.entity.OperationLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationLogDao {

    /**
     * 插入操作日志
     *
     * @param operationLog 操作日志实体
     * @return 影响行数
     */
    int insertOperationLog(OperationLogEntity operationLog);

    /**
     * 获取所有操作日志
     *
     * @return 操作日志列表
     */
    List<OperationLogEntity> getAllOperationLogs();

    /**
     * 分页获取操作日志
     *
     * @param offset 偏移量
     * @param size 页面大小
     * @param operationModule 操作模块
     * @param operationType 操作类型
     * @param operator 操作人
     * @return 操作日志列表
     */
    List<OperationLogEntity> getOperationLogsByPage(@Param("offset") int offset, 
                                                   @Param("size") int size, 
                                                   @Param("operationModule") String operationModule, 
                                                   @Param("operationType") String operationType, 
                                                   @Param("operator") String operator);

    /**
     * 统计符合条件的操作日志数量
     *
     * @param operationModule 操作模块
     * @param operationType 操作类型
     * @param operator 操作人
     * @return 日志数量
     */
    int countOperationLogs(@Param("operationModule") String operationModule, 
                          @Param("operationType") String operationType, 
                          @Param("operator") String operator);
}
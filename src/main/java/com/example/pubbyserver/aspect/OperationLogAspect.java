package com.example.pubbyserver.aspect;

import com.example.pubbyserver.entity.OperationLogEntity;
import com.example.pubbyserver.service.OperationLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperationLogService operationLogService;

    @Around("@annotation(operationLog)")
    public Object recordLog(ProceedingJoinPoint joinPoint, com.example.pubbyserver.annotation.OperationLog operationLog) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 获取操作人
        String operator = getCurrentUsername(joinPoint, operationLog);

        // 获取IP地址
        String ipAddress = getClientIpAddress();

        // 创建操作日志实体
        OperationLogEntity log = new OperationLogEntity();
        log.setOperationModule(operationLog.module());
        log.setOperationType(operationLog.type());
        log.setOperator(operator);
        log.setOperationTime(new Date());
        log.setIpAddress(ipAddress);

        try {
            // 执行原方法
            Object result = joinPoint.proceed();

            // 记录执行时间
            log.setExecutionTime(System.currentTimeMillis() - startTime);
            log.setStatus(1); // 成功

            // 保存操作日志
            operationLogService.saveOperationLog(log);

            return result;
        } catch (Exception e) {
            // 记录执行时间
            log.setExecutionTime(System.currentTimeMillis() - startTime);
            log.setStatus(0); // 失败

            // 保存操作日志
            operationLogService.saveOperationLog(log);

            // 抛出异常
            throw e;
        }
    }

    /**
     * 获取当前登录用户名
     *
     * @return 用户名
     */
    private String getCurrentUsername(ProceedingJoinPoint joinPoint, com.example.pubbyserver.annotation.OperationLog operationLog) {
        try {
            // 对于登录操作特殊处理：从方法参数中获取用户名
            if ("LOGIN".equals(operationLog.type())) {
                // 从joinPoint参数中查找LoginRequest对象
                Object[] args = joinPoint.getArgs();
                for (Object arg : args) {
                    if (arg instanceof com.example.pubbyserver.entity.LoginRequest) {
                        return ((com.example.pubbyserver.entity.LoginRequest) arg).getUsername();
                    }
                }
            }
            // 尝试从SecurityContext获取用户名
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                return authentication.getName();
            }
        } catch (Exception e) {
            // 忽略异常
        }
        return "system"; // 默认值
    }

    /**
     * 获取客户端IP地址
     *
     * @return IP地址
     */
    private String getClientIpAddress() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                return request.getRemoteAddr();
            }
        } catch (Exception e) {
            // 忽略异常
        }
        return "unknown"; // 默认值
    }
}
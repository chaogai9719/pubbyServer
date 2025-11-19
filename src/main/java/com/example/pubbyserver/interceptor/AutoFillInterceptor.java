package com.example.pubbyserver.interceptor;

import com.example.pubbyserver.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * MyBatis拦截器，用于自动填充BaseEntity中的公共字段
 * 包括创建人、创建时间、修改人、修改时间
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class AutoFillInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];

        // 获取当前登录用户
        String currentUser = getCurrentUser();

        // 获取SQL命令类型（INSERT或UPDATE）
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 处理参数对象
        if (parameter != null) {
            if (sqlCommandType == SqlCommandType.INSERT && parameter instanceof BaseEntity) {
                // 插入操作，设置创建和更新字段
                BaseEntity baseEntity = (BaseEntity) parameter;
                Date now = new Date();
                
                // 设置创建字段
                setFieldValue(baseEntity, "createTime", now);
                setFieldValue(baseEntity, "createBy", currentUser);
                
                // 设置更新字段
                setFieldValue(baseEntity, "updateTime", now);
                setFieldValue(baseEntity, "updateBy", currentUser);
            } else if (sqlCommandType == SqlCommandType.UPDATE && parameter instanceof BaseEntity) {
                // 更新操作，只设置更新字段
                BaseEntity baseEntity = (BaseEntity) parameter;
                Date now = new Date();
                
                // 只设置更新字段
                setFieldValue(baseEntity, "updateTime", now);
                setFieldValue(baseEntity, "updateBy", currentUser);
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以设置一些自定义属性
    }

    /**
     * 获取当前登录用户
     *
     * @return 当前登录用户名，如果未登录则返回"system"
     */
    private String getCurrentUser() {
        try {
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
     * 通过反射设置对象字段值
     *
     * @param obj       目标对象
     * @param fieldName 字段名
     * @param value     字段值
     */
    private void setFieldValue(Object obj, String fieldName, Object value) {
        try {
            // 先尝试从父类获取字段(BaseEntity)
            Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            // 如果在父类中找不到，尝试在当前类中查找
            try {
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(obj, value);
            } catch (Exception ex) {
                // 忽略设置失败的情况
            }
        }
    }
}
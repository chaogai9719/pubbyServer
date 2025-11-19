package com.example.pubbyserver.config;

import com.example.pubbyserver.interceptor.AutoFillInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class MyBatisConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private AutoFillInterceptor autoFillInterceptor;

    @PostConstruct
    public void addPageInterceptor() {
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(autoFillInterceptor);
        }
    }
}
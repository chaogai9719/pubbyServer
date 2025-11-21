package com.example.pubbyserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.example.pubbyserver.dao")
@EnableAspectJAutoProxy
public class PubbyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubbyServerApplication.class, args);
    }

}

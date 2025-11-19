package com.example.pubbyserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.pubbyserver.dao")
public class PubbyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubbyServerApplication.class, args);
    }

}

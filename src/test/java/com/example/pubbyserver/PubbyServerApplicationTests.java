package com.example.pubbyserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class PubbyServerApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 示例：生成admin用户的加密密码
        String rawPassword = "123456";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);

        // 验证密码匹配
        boolean matches = encoder.matches(rawPassword, "$2a$10$PO8Jo83HvgK5iYUr..Og5u1P5c18EBi2f.EMhRhAm8gKrqdL7THce");
        System.out.println("Password matches: " + matches);

    }

}

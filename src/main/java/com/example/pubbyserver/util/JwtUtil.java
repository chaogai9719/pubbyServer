package com.example.pubbyserver.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtUtil {
    
    // 密钥
    private String SECRET_KEY = "pubby_server_secret_key";
    
    // Token有效期 7天
    private long JWT_TOKEN_VALIDITY = 7 * 24 * 60 * 60;
    
    // 存储已注销token的过期时间，key为token，value为过期时间
    private Map<String, Long> invalidTokens = new ConcurrentHashMap<>();
    
    // 从token中获取用户名
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    // 从token中获取过期时间
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    // 解析token中的声明
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
    // 检查token是否过期
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    // 检查token是否已被注销
    public Boolean isTokenInvalid(String token) {
        Long invalidUntil = invalidTokens.get(token);
        if (invalidUntil == null) {
            return false; // 未被注销
        }
        // 如果当前时间超过了预设的过期时间，则从映射中移除，并认为token有效（因为已经过了原本的过期时间）
        if (System.currentTimeMillis() > invalidUntil) {
            invalidTokens.remove(token);
            return false;
        }
        return true; // 已被注销且仍在无效期内
    }
    
    // 生成token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }
    
    // 创建token
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    
    // 验证token
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token) && !isTokenInvalid(token));
    }
    
    // 注销token，设置其在原本过期时间之前都为无效状态
    public void invalidateToken(String token) {
        Date expiration = getExpirationDateFromToken(token);
        // 设置该token在原本过期时间之前都为无效状态
        invalidTokens.put(token, expiration.getTime());
    }
}
package com.xhn.security.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类 - 使用 jjwt 库生成和验证 JWT token
 *
 * @author xhn
 */
@Component
public class JwtUtil {

    // 注意：生产环境应该将密钥存储在配置文件中，并使用更强的密钥
    private final Key key = Keys.hmacShaKeyFor("ChangeThisSecretToAStrongSecretKeyForProductionUseAtLeast32Bytes".getBytes());
    private final long expirationMillis = 1000L * 60 * 60 * 24; // 24 小时

    /**
     * 生成 JWT token
     *
     * @param subject 主题（通常是用户名）
     * @param claims  自定义声明
     * @return JWT token
     */
    public String generateToken(String subject, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public String generateRefreshToken(String subject) {
        long now = System.currentTimeMillis();
        long refreshExpirationMillis = 1000L * 60 * 60 * 24 * 7; // 7 天
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + refreshExpirationMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 JWT token
     *
     * @param token JWT token
     * @return Claims 对象
     * @throws JwtException 如果 token 无效
     */
    public Jws<Claims> parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }


    public Date getExpirationDate(String token) {
        Claims claims = parseToken(token).getBody();
        return claims.getExpiration();

    }

    /**
     * 验证 token 是否有效
     *
     * @param token JWT token
     * @return true 如果有效，否则 false
     */
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}


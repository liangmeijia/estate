package com.lmj.estate.utils;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
@Slf4j
public class JwtUtils {

    private static String SECRET_KEY; // 随机密钥
    static {
        SECRET_KEY = generateRandomKey();
    }
    /**
     * 生成JWT token
     * @param subject 用户信息
     * @param expirationMillis 登录过期时间
     * @return
     */
    public static String generateJwtToken(String subject, long expirationMillis) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMillis);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 解析JWT
     * @param jwtToken  JWT token
     * @return 用户信息
     */
    public static String parseJwtToken(String jwtToken) {
        if(StrUtil.isBlank(jwtToken)){
            throw new RuntimeException("jwtToken 为空");
        }
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.getSubject();
    }

    /**
     * 生成随机密钥
     * @return 密钥
     */
    public static String generateRandomKey() {
        // 生成256位的随机字节数组
        byte[] keyBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);

        // 将字节数组转换为Base64编码的字符串
        String key = Base64.getEncoder().encodeToString(keyBytes);
        log.debug("密钥："+key);

        return key;
    }

}


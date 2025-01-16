package com.lmj.estate.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author lmj
 * @version 1.0
 * @description 密码加密
 * @date 2025/01/16 10:59:15
 */
public class PasswordUtil {
    /**
     * 生成并返回哈希后的密码
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String hashPassword(String password) {
        // 生成盐值
        String salt = BCrypt.gensalt();
        // 生成哈希值
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }

    /**
     * 验证密码是否正确
     * @param password 原始密码
     * @param hashedPassword 加密后的密码
     * @return 是否正确
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

package com.shuili.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author huhu
 * @date 2024-03-07 15:19:02
 *密码加密工具类
 */
public class EncryUtil {
    // 加密算法
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 密码比对
    public static boolean comparePasswords(String inputPassword, String storedPassword) {
        String hashedInputPassword = encryptPassword(inputPassword);
        assert hashedInputPassword != null;
        return hashedInputPassword.equals(storedPassword);
    }
}

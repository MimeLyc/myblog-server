package com.yochalyc.myblog.blog.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class Md5Util {

    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5_16(String raw) {
        return md5_32(raw).substring(16);
    }

    public static String md5_32(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            byte[] bytes = md.digest();

            StringBuilder su = new StringBuilder();
            for (int offset = 0, bLen = bytes.length; offset < bLen; offset++) {
                String haxHex = Integer.toHexString(bytes[offset] & 0xFF);
                if (haxHex.length() < 2) {
                    su.append("0");
                }
                su.append(haxHex);
            }

            return su.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get salt
     *
     * @param capacity lenth of salt
     * @return
     */
    public static String randomSalt(int capacity) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append(hex[random.nextInt(16)]);
        }

        return sb.toString();
    }

    public static String encryptWithSalt(String raw, String salt) {
        String toEncrypt = String.join("", salt, raw);
        return md5_32(toEncrypt);
    }

    public static String randomToken_16() {
        return md5_16(UUID.randomUUID().toString());
    }

    public static Boolean isPasswordValid(String pwdHash, String salt, String inputPwd) {
        String hashedPwd = Md5Util.encryptWithSalt(inputPwd, salt);
        return StringUtils.equals(pwdHash, hashedPwd);
    }

}

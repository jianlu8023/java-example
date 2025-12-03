package com.github.jianlu8023.utils.web.utils;

import java.security.*;

public class MD5Utils {

    public static final String SEPARATOR_CHAR = "#";
    public static String md5(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(input.getBytes());
            byte[] byteArray = md5.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : byteArray) {
                // 一个byte格式化成两位的16进制，不足两位高位补零
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String md5(Object... strs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (i > 0) {
                builder.append(SEPARATOR_CHAR);
            }
            if (strs[i] != null) {
                builder.append(strs[i].toString());
            }
        }
        return md5(builder.toString());
    }
}

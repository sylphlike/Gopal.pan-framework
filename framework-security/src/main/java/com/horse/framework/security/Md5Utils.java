package com.horse.framework.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class Md5Utils {

    private static final int CODE_LENGTH = 32;

    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        StringBuilder stringBuffer = new StringBuilder();
        // 16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);

        stringBuffer.append(md5code);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < CODE_LENGTH - md5code.length(); i++) {
            stringBuffer.insert(0,"0");
        }
        return stringBuffer.toString();
    }

}

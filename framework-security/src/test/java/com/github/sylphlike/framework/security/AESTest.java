package com.github.sylphlike.framework.security;

import java.util.StringJoiner;

/**
 * <p>  time 17:36 2021/04/14  星期三 </p>
 * <p> email 15923508369@163.com     </P>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public class AESTest {

    public static void main(String[] args) {
        String stringKey = AESEncryptUtils.createStringKey();
        System.out.println("秘钥 " + stringKey);
        StringJoiner stringJoiner = new StringJoiner("");
        for (int x = 0; x < 6144; x ++){
            stringJoiner.add("测");
        }

        String encrypt = AESEncryptUtils.encrypt(stringJoiner.toString(), stringKey);
        System.out.println("密文 "+ encrypt);
        System.out.println("密文长度 " + encrypt.length()); // 16408
        String decrypt = AESEncryptUtils.decrypt(encrypt, stringKey);
        System.out.println("解密 " + decrypt);
        System.out.println(decrypt.length());


    }
}

package com.horse.framework.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * AES对称加密工具类
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class AESEncryptUtils {

    private static final String PASSWORD            = "Gopal.pan";
    private static final String ALGORITHM           = "AES/ECB/PKCS5Padding";
    private static final String CHAR_SET            = "utf-8";
    private static final String KEY_ALGORITHM       = "AES";
    private static final int    ENCRYPT_KEY_LENGTH  =  128;


    /**
     * 生成AES秘钥
     * @return
     */
    public static String createStringKey() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            //AES 要求密钥长度为 128
            keyGenerator.init(ENCRYPT_KEY_LENGTH, new SecureRandom(PASSWORD.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            return Hex.encodeHexString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    /**
     * 生成AES秘钥
     * @return
     */
    public static SecretKey createSecretKey() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            //AES 要求密钥长度为 128
            keyGenerator.init(ENCRYPT_KEY_LENGTH, new SecureRandom(PASSWORD.getBytes()));
            return  keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;
    }






    /**
     * 转换密钥
     * @param key   二进制密钥
     * @return 密钥
     */
    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }


    /**
     * 加密
     * @param data
     * @param stringKey
     * @return
     */
    public static String encrypt(String data,String stringKey){
        try {
            Key key = toKey(Hex.decodeHex(stringKey.toCharArray()));
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encodeBase64String(cipher.doFinal(data.getBytes(CHAR_SET)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密
     * @param data
     * @param stringKey
     * @return
     */
    public static String decrypt(String data,String stringKey){

        try {
            Key key = toKey(Hex.decodeHex(stringKey.toCharArray()));

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(Base64.decodeBase64(data));

            return new String(bytes,CHAR_SET);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }







}
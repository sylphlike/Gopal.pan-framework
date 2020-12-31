package com.github.sylphlike.framework.security;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>  time 10/09/2019 15:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * RSA非对称加密工具类
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class RSAEncryptUtils {

    public  static final String PUBLIC_KEY       = "public_key";
    public  static final String PRIVATE_KEY      = "private_key";
    private static final String KEY_ALGORITHM    = "RSA";
    private static final String CHAR_SET         = "utf-8";
    private static final int    KEY_SIZE         = 2048;




    /**
     * <p>  time 15:46 2019/09/10 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * 生成RSA公私匙
     *  获取公私 pair.get(RSAEncryptUtils.PUBLIC_KEY)
     *  获取私匙 pair.get(RSAEncryptUtils.PRIVATE_KEY)
     * @param
     * @return   java.util.Map<java.lang.String,java.lang.String>  生成的RSA密匙对
     * @author   Gopal.pan
     */
    public static Map<String, String> generateRSAPair() {
        Map<String, String> pair = new HashMap<String, String>();
        KeyPairGenerator keyPairGen;
        try {
            keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("get RSA key pair generator failed", e);
        }
        keyPairGen.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        pair.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
        pair.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()));
        return pair;
    }



    /**
     * <p>  time 16:47 2019/10/29 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * 公匙加密
     * @param data  待加密数据
     * @param key
     * @return   java.lang.String
     * @author   Gopal.pan
     */
    public static String encryptByPublicKey(String data, String key) throws Exception {

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] dateByte = cipher.doFinal(data.getBytes(CHAR_SET));

        return Base64.encodeBase64String(dateByte);
    }



    /**
     * <p>  time 16:47 2019/10/29 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * 公匙解密
     * @param data
     * @param key
     * @return   java.lang.String
     * @author   Gopal.pan
     */
    public static String decryptByPublicKey(String data, String key) throws Exception {

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec( Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return new String(cipher.doFinal(Base64.decodeBase64(data)));

    }




    /**
     * <p>  time 16:49 2019/10/29 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * 私匙加密
     * @param data
     * @param key
     * @return   java.lang.String
     * @author   Gopal.pan
     */
    public static String encryptByPrivateKey(String data, String key) throws Exception {

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
    }



    /**
     * <p>  time 16:49 2019/10/29 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * 私匙解密
     * @param data
     * @param key
     * @return   java.lang.String
     * @author   Gopal.pan
     */
    public static String decryptByPrivateKey(String data, String key) throws Exception {

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataByte = cipher.doFinal(Base64.decodeBase64(data));

        return new String(dataByte);
    }

}

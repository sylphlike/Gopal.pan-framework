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
 * RSA非对称加密工具类
 * <p>  time 10/09/2019 15:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
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
     * 生成RSA公私匙
     * <p>获取公私 pair.get(RSAEncryptUtils.PUBLIC_KEY) .获取私匙 pair.get(RSAEncryptUtils.PRIVATE_KEY)
     * <p>  time 16:03 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.util.Map
     * @author  Gopal.pan
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
     * 公匙加密
     * <p>  time 15:59 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param data  待加密数据
     * @param key   公钥
     * @return  java.lang.String
     * @throws  Exception ex
     * @author  Gopal.pan
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
     * 公匙解密
     * <p>  time 15:58 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param data  业务数据
     * @param key   公钥
     * @return  java.lang.String
     * @throws  Exception ex
     * @author  Gopal.pan
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
     * 私钥加密
     * <p>  time 15:58 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param data  业务数据
     * @param key   私钥
     * @return  java.lang.String
     * @throws  Exception ex
     * @author  Gopal.pan
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
     * 私匙解密
     * <p>  time 16:01 2021/1/4 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param data 业务数据
     * @param key  私钥
     * @return  java.lang.String
     * @throws  Exception ex
     * @author  Gopal.pan
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

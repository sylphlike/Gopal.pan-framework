package com.github.sylphlike.framework.security;

/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * 加解密签名与验密方式
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public enum SecurityTypeEnums {

    /**
     * 加密类型
     */
    MD5("MD5"),
    AES("AES"),
    RSA("RSA"),
    ;



    SecurityTypeEnums(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public static SecurityTypeEnums getByCode(String code) {
        for (SecurityTypeEnums securityTypeEnums : SecurityTypeEnums.values()) {
            if (securityTypeEnums.getCode().equals(code)) {
                return securityTypeEnums;
            }
        }
        return null;
    }
}

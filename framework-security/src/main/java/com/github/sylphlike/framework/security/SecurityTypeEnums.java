package com.github.sylphlike.framework.security;

/**
 * 加解密签名与验密方式
 * <p>  time 17:56 2010/10/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public enum SecurityTypeEnums {

    MD5("MD5"),
    AES("AES"),
    RSA("RSA"),
    SM2("SM2"),
    ;



    SecurityTypeEnums(String code) {
        this.code = code;
    }

    private final String code;

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

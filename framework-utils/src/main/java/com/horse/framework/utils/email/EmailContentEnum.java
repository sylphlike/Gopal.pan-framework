package com.horse.framework.utils.email;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public enum EmailContentEnum {

    HTML("HTML", "html格式"),

    TEXT("TEXT", "标准文本格式"),

   ;

    private String code;

    private String desc;


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    EmailContentEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}

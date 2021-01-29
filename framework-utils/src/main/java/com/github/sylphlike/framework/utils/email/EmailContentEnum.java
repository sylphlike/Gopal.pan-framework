package com.github.sylphlike.framework.utils.email;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
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

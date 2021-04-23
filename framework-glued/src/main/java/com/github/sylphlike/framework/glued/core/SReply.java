package com.github.sylphlike.framework.glued.core;


import com.github.sylphlike.framework.norm.RCode;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public enum  SReply implements RCode {


    GLUED_NOT_SUPPORT_OPERATION         ("C00X977000","不支持该类型操作"),

    ;




    private final String code;
    private final String message;



    public String getCode() { return code; }
    public String getMessage() {
        return message;
    }


    SReply(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

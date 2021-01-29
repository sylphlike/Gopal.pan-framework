package com.github.sylphlike.framework.glued.core;


import com.github.sylphlike.framework.norm.RCode;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public enum  SReply implements RCode {


    // 900001-910000统一登录中间件错误码区间
    GLUED_NOT_SUPPORT_OPERATION                      (97700,"不支持该类型操作"),

    ;




    private final int code;

    private final String message;



    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }


    SReply(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

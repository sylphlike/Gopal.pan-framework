package com.github.sylphlike.framework.glued.core;


import com.github.sylphlike.framework.norm.RCode;

/**
 * <p>  time 17:56 2017/03/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public enum  SReply implements RCode {


    GLUED_NOT_SUPPORT_OPERATION         ("C00X977000","不支持该类型操作", ""),

    ;




    private final String code;
    private final String msg;
    private final String subMsg;






    SReply(String code, String msg, String subMsg) {
        this.code = code;
        this.msg = msg;
        this.subMsg = subMsg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getSubMsg() {
        return subMsg;
    }
}

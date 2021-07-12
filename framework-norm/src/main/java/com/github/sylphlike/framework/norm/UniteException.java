package com.github.sylphlike.framework.norm;

import java.io.Serializable;

/**
 * 系统异常,系统中定义异常需强制继承该异常声明
 * <p>  time 17:56 2018/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class UniteException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7993953108123296363L;

    public UniteException(String message) {super(message); }

    public UniteException(RCode rCode){
        super(rCode.getCode());
        this.code   = rCode.getCode();
        this.msg    = rCode.getMsg();
        this.subMsg = rCode.getSubMsg();
    }

    public UniteException(RCode rCode,String subMsg){
        super( rCode.getMsg() + CharsetUtil.STRING_ENGLISH_COMMA_SPACE + subMsg);
        this.code   = rCode.getCode();
        this.msg    = rCode.getMsg();
        this.subMsg = subMsg;
    }

    /** 错误编码 */
    protected String code;

    /** 错误摘要描述 */
    protected String msg;

    /** 错误详细描述 */
    protected String subMsg;

    public String getCode() {  return code; }
    public String getMsg() { return msg;}
    public String getSubMsg() { return subMsg;}
}

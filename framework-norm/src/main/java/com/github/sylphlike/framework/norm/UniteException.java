package com.github.sylphlike.framework.norm;

import java.io.Serializable;

/**
 * 系统异常,系统中定义异常需强制继承该异常声明
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class UniteException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7993953108123296363L;

    public UniteException(String message) {
        super(message);
    }

    /** 错误主码 */
    protected int code;

    /** 错误描述 */
    protected String message;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}

package com.github.sylphlike.framework.security;

import java.io.Serializable;


/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class SecurityException extends Exception implements Serializable {

    /**
     * 错误描述
     */
    private String errorMsg;


    public SecurityException(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

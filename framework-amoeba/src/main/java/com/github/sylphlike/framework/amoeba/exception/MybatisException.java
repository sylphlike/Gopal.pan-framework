package com.github.sylphlike.framework.amoeba.exception;

import java.io.Serializable;

/**
 * <p>  time 17:56 2019/05/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class MybatisException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1695036681341844113L;

    /** 错误描述*/
    private String errorMsg;


    public MybatisException(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

package com.github.sylphlike.framework.utils;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;


    public UtilException(String message) {
        super(message);
    }


    public UtilException(Throwable cause) {
        super(cause);
    }

}

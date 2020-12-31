package com.github.sylphlike.framework.utils;

/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
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

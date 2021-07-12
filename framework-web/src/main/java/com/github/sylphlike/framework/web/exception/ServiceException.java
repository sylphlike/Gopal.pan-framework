package com.github.sylphlike.framework.web.exception;

import com.github.sylphlike.framework.norm.RCode;
import com.github.sylphlike.framework.norm.UniteException;


/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </p>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class ServiceException extends UniteException {

    private static final long serialVersionUID = -1695036681341844113L;


    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(RCode rCode) {
        super(rCode);
    }

    public ServiceException(RCode rCode, String subMsg) {
        super(rCode, subMsg);
    }
}

package com.github.sylphlike.framework.web.exception;

import com.github.sylphlike.framework.norm.CharsetUtil;
import com.github.sylphlike.framework.norm.RCode;
import com.github.sylphlike.framework.norm.UniteException;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class ServiceException extends UniteException {

    private static final long serialVersionUID = -1695036681341844113L;



    public ServiceException(RCode message) {
        super(message.getMessage());
        this.code    = message.getCode();
        this.message = message.getMessage();
    }


    public ServiceException(RCode rCode, String detailMessage) {
        super( rCode.getMessage() + CharsetUtil.STRING_ENGLISH_COMMA_SPACE + detailMessage);
        this.code    = rCode.getCode();
        this.message = rCode.getMessage() + CharsetUtil.STRING_ENGLISH_COMMA_SPACE + detailMessage;
    }





}

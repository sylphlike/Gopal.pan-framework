package com.github.sylphlike.framework.glued.common.exception;


import com.github.sylphlike.framework.norm.CharsetUtil;
import com.github.sylphlike.framework.norm.RCode;
import com.github.sylphlike.framework.norm.UniteException;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class GluedException extends UniteException {

    private static final long serialVersionUID = -1695036681341844113L;



    public GluedException(RCode message) {
        super(message.getMessage());
        this.code    = message.getCode();
        this.message = message.getMessage();
    }


    public GluedException(RCode message, String detailMessage) {
        super( message.getMessage() + CharsetUtil.STRING_ENGLISH_COMMA_SPACE + detailMessage);
        this.code    = message.getCode();
        this.message = message.getMessage() + CharsetUtil.STRING_ENGLISH_COMMA_SPACE + detailMessage;
    }





}

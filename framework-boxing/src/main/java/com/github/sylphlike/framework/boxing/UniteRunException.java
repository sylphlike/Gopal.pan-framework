package com.github.sylphlike.framework.boxing;

import java.io.Serializable;

/**
 * <p>  time 17:56 2019/03/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class UniteRunException extends Exception implements Serializable {


    private static final long serialVersionUID = -1933870715561559962L;

    public UniteRunException(String errorMsg) {
        super(errorMsg);
    }
}

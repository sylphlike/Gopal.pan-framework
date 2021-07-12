package com.github.sylphlike.framework.boxing;

import java.io.Serializable;

/**
 * <p>  time 17:56 2019/05/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class UniteConfigException extends Exception implements Serializable {

    private static final long serialVersionUID = -1695036681341844113L;

    public UniteConfigException(String errorMsg) {
        super(errorMsg);
    }
}

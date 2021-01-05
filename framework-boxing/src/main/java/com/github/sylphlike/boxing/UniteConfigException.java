package com.github.sylphlike.boxing;

import java.io.Serializable;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class UniteConfigException extends Exception implements Serializable {

    private static final long serialVersionUID = -1695036681341844113L;

    public UniteConfigException(String errorMsg) {
        super(errorMsg);
    }
}

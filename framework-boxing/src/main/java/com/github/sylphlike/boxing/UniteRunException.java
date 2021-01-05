package com.github.sylphlike.boxing;

import java.io.Serializable;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class UniteRunException extends Exception implements Serializable {


    private static final long serialVersionUID = -1933870715561559962L;

    public UniteRunException(String errorMsg) {
        super(errorMsg);
    }
}

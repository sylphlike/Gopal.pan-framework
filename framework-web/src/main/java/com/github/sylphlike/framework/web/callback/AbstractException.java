package com.github.sylphlike.framework.web.callback;


import com.github.sylphlike.framework.norm.Response;

/**
 * <p>  time 21/09/2020 14:32  星期一 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public abstract class AbstractException<T> {


    /**
     * 事物执行抽象类
     * @return
     * @throws Exception
     */
    public abstract Response<T> execute() throws Exception;
}

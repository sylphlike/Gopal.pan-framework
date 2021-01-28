package com.github.sylphlike.framework.web.config.callback;


import com.github.sylphlike.framework.norm.Response;

/**
 * <p>  time 21/09/2020 14:32  星期一 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 * @author Gopal.pan
 * @version 1.0.0
 */
public abstract class AbstractException<T> {

    /**
     * 事物执行抽象类
     * <p>  time 17:13 2021/1/28 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  com.github.sylphlike.framework.norm.Response<T>
     * @throws  Exception ex
     * @author  Gopal.pan
     */
    public abstract Response<T> execute() throws Exception;
}

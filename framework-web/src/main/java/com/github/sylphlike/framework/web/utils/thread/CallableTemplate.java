package com.github.sylphlike.framework.web.utils.thread;

import java.util.concurrent.Callable;


/**
 * 多线程处理类
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </p>
 * @author Gopal.pan
 * @version 1.0.0
 */

public abstract class CallableTemplate<V> implements Callable<V> {

    /**
     * 前置处理，子类可以Override该方法
     */
    public void beforeProcess() {

    }


    /**
     * 处理业务逻辑的方法,需要子类去Override
     * @return
     */
    public abstract V process();



    /**
     * 后置处理，子类可以Override该方法
     */
    public void afterProcess() {

    }
    @Override
    public V call() throws Exception {
        beforeProcess();
        V result = process();
        afterProcess();
        return result;
    }

}

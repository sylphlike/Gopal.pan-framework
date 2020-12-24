package com.horse.framework.web.callback;

import com.horse.framework.norm.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 手动事物回调执行方法抽象类，
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public abstract class AbstractTransaction<T>{

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTransaction.class);

    public AbstractTransaction(){}

    /** 日志标题*/
    private String logTitle;

    /**执行方法*/
    private String logMethod;

    /** 参数*/
    private Object requestArgs;



    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }
    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod;
    }
    public void setRequestArgs(Object requestArgs) {
        this.requestArgs = requestArgs;
    }



    public AbstractTransaction<T> logTitle(String title) {
        setLogTitle(title);
        return this;
    }

    public AbstractTransaction<T> logMethod(String method) {
        setLogMethod(method);
        return this;
    }

    public AbstractTransaction<T> requestArgs(Object... obj) {
        setRequestArgs(obj);
        return this;
    }



    public AbstractTransaction<T> recordTransactionContentLog(){
        LOGGER.info("【framework-web】事物执行日志,logTitle[{}]; logMethod[{}]; requestArgs[{}]",logTitle,logMethod, requestArgs);
        return this;
    }




    /**
     * <p>  time 16:32 2020/11/25 【HH:mm yyyy/MM/dd】  </p>
     * <p> email 15923508369@163.com </p>
     *  事物执行抽象类，业务接口实现具体逻辑
     *
     * @return   com.horse.framework.norm.Response<T>
     * @author   Gopal.pan
     */
    public abstract Response<T> execute() throws  Exception;

}

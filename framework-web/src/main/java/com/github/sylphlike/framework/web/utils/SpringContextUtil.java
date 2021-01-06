package com.github.sylphlike.framework.web.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * spring 应用上下文
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * @param applicationContext applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }



    /**
     *
     * <p>  time 17:18 2021/1/6 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param name   bean名称
     * @return  java.lang.Object
     * @throws  BeansException ex
     * @author  Gopal.pan
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }




    /**
     * 根据类型获取bean
     * <p>  time 17:18 2021/1/6 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param clazz  class
     * @return  T    类实例
     * @author  Gopal.pan
     */
    public static <T> T getBean(Class<T> clazz) {
        T t = null;
        Map<String, T> map = applicationContext.getBeansOfType(clazz);
        for (Map.Entry<String, T> entry : map.entrySet()) {
            t = entry.getValue();
        }
        return t;
    }


    /**
     * 根据类型获取该实例下的所有子类
     * <p>  time 17:20 2021/1/6 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param clazz  类实例
     * @return  java.util.Map<java.lang.String,org.apache.poi.ss.formula.functions.T>
     * @author  Gopal.pan
     */
    public static <T> Map<String, T>  getBeansOfType(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }



}

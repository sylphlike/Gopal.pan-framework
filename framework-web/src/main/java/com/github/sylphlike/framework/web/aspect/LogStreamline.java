package com.github.sylphlike.framework.web.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 通用日志注解  实现日志打印精简，应用该注解后打印的日志只会输出code码和摘要信息 不会打印具体返回的业务数据
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogStreamline {
}
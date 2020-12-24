package com.horse.framework.web.aspect;

import com.horse.framework.norm.CharsetUtil;
import com.horse.framework.web.utils.ParamFormat;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * third 请求、响应日志记录 aspect
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Aspect
@Component
public class ThirdLogAspect {

    protected Logger logger =  LoggerFactory.getLogger(ThirdLogAspect.class);

    @Pointcut("execution(public * com.horse.framework.web.BaseThirdService+.*(..))")
    public void thirdRequestLog(){}

    @Before("thirdRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        int index = declaringTypeName.lastIndexOf(".");
        logger.info("【framework-web】third请求,执行方法[{}]; 参数[{}]",
                StringUtils.join(declaringTypeName.substring(++index) , CharsetUtil.CHAR_ENGLISH_DOT, joinPoint.getSignature().getName()),
                ParamFormat.format(((MethodSignature)joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "thirdRequestLog()")
    public void doAfterReturning(Object ret) {
        logger.info("【framework-web】third响应,[{}]", ret);
    }

}

package com.github.sylphlike.framework.web.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sylphlike.framework.basis.UserHelper;
import com.github.sylphlike.framework.norm.CharsetUtil;
import com.github.sylphlike.framework.norm.Response;
import com.github.sylphlike.framework.web.exception.AssertUtils;
import com.github.sylphlike.framework.web.utils.ParamFormat;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * web增强处理
 * <p> 1 入参出参日志记录
 *     2 移除 ThreadLocal数据
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */
@Aspect
@Component
public class WebStrengthenAspect {
    private final Logger LOGGER =  LoggerFactory.getLogger(WebStrengthenAspect.class);

    private final ObjectMapper mapper;

    public WebStrengthenAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Pointcut("execution(public * com.github.sylphlike.framework.web.BaseController+.*(..))")
    public void webRequestAspectLog(){}

    @Before( value = "webRequestAspectLog()")
    public void doBefore(JoinPoint joinPoint) {
        if(joinPoint.getSignature().getName().equals("setGeneralReqAndRes")){
            return;
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        AssertUtils.notNull(attributes,"ServletRequestAttributes is null");
        HttpServletRequest request = attributes.getRequest();

        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        String description =  requestAnnotationName(sign.getMethod());

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        LOGGER.info("【framework-web】【request】[{}][{}][{}],params[{}]",
                request.getMethod(),
                description,
                request.getRequestURI(),
                ParamFormat.format(methodSignature.getParameterNames(), joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webRequestAspectLog()")
    public void doAfterReturning(JoinPoint joinPoint,Object ret) {
        try {
            MethodSignature sign = (MethodSignature) joinPoint.getSignature();
            Method method = sign.getMethod();

            if(!joinPoint.getSignature().getName().equals("setGeneralReqAndRes")){
                LogStreamline logStreamline = method.getAnnotation(LogStreamline.class);
                if (null != logStreamline){
                    if(ret instanceof Response){
                        Response<?> response = (Response<?>) ret;
                        Response<Object> logStreamLine = new Response<>();
                        logStreamLine.setCode(response.getCode());
                        logStreamLine.setMessage(response.getMessage());
                        logStreamLine.setTimestamp(response.getTimestamp());
                        LOGGER.info("【framework-web】【response】过滤业务参数[{}]", mapper.writeValueAsString( logStreamLine) );
                    }else {
                        LOGGER.info("【framework-web】【response】当前返回实体不为系统定义类型,[{}]",ret);
                    }
                }else {
                    if(ret instanceof Response){
                        LOGGER.info("【framework-web】【response】data[{}]", mapper.writeValueAsString( ret));
                    }else {
                        LOGGER.info("【framework-web】【response】,当前返回实体不为系统定义类型,[{}]",ret);
                    }
                }
                //移除用户本地线程数据
                UserHelper.IDENTITY_ID.remove();
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("【framework-web】response,json格式化响应参数异常",e);
        }

    }




    private String requestAnnotationName(Method method) {

        String description = "";
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        if(null != requestMapping){
            description = requestMapping.name();
        }else if (null != getMapping){
            description = getMapping.name();
        }else if (null != postMapping){
            description = postMapping.name();
        }else if (null != deleteMapping){
            description = deleteMapping.name();
        }else if (null != putMapping)
            description = putMapping.name();

        return description;
    }

}

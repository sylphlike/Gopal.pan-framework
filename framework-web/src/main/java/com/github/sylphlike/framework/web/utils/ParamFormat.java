package com.github.sylphlike.framework.web.utils;

import com.github.sylphlike.framework.basis.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public class ParamFormat {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParamFormat.class);

    public static String format(String[] parameterNames, Object[] args){

        Map<String,Object> map = new HashMap<>();
        try {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof MultipartFile || arg instanceof ServletRequest || arg instanceof ServletResponse
                        || arg instanceof BeanPropertyBindingResult) {
                    LOGGER.info("【framework-web】请求参数格式化,参数类型在不需要格式化处理");
                    continue;
                }
                map.put(parameterNames[i],arg);
            }
            return JsonConfig.mapper().writeValueAsString(map);

        } catch (Exception e) {
            LOGGER.error("【framework-web】请求参数格式化处理异常,返回原始参数", e);
            return  Arrays.toString(args);
        }


    }
}


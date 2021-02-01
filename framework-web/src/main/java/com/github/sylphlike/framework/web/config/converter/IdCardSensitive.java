package com.github.sylphlike.framework.web.config.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.sylphlike.framework.utils.general.SensitiveUtils;

/**
 * 身份证号脱敏转换器
 * <p>  time 28/01/2021 16:12  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */
public class IdCardSensitive extends StdConverter<String,String> {
    @Override
    public String convert(String value) {
        return SensitiveUtils.idCardNum(value);
    }
}

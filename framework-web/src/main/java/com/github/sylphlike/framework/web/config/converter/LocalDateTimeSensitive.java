package com.github.sylphlike.framework.web.config.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.sylphlike.framework.utils.enums.DateEnums;
import com.github.sylphlike.framework.utils.general.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * LocalDateTime 脱敏转换器
 * <p>  time 28/01/2021 16:36  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */
public class LocalDateTimeSensitive extends StdConverter<LocalDateTime,String> {
    @Override
    public String convert(LocalDateTime value) {
        String out = "";
        if(ObjectUtils.isNotEmpty(value)){
            String stringDate = DateUtils.date2String(value, DateEnums.HYPHEN_YYYYMMddHHmmss.getPatterns());
            out = StringUtils.left(stringDate, 4).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(stringDate, 8), StringUtils.length(stringDate), "*"), "******"));
        }
        return out;
    }
}

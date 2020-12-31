package com.github.sylphlike.framework.utils.enums;

/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public enum DateEnums {

    //中划线
    HYPHEN_YYYYMM("yyyy-MM"),
    HYPHEN_YYYYMMdd("yyyy-MM-dd"),
    HYPHEN_YYYYMMddHHmm("yyyy-MM-dd HH:mm"),
    HYPHEN_YYYYMMddHHmmss("yyyy-MM-dd HH:mm:ss"),
    HYPHEN_YYYYMMddHHmmssSSS("yyyy-MM-dd HH:mm:ss:SSS"),

    //斜划线
    SLASH_YYYYMM("yyyy/MM"),
    SLASH_YYYYMMdd("yyyy/MM/dd"),
    SLASH_YYYYMMddHHmm("yyyy/MM/dd HH:mm"),
    SLASH_YYYYMMddHHmmss("yyyy/MM/dd HH:mm:ss"),
    SLASH_YYYYMMddHHmmssSSS("yyyy/MM/dd HH:mm:ss:SSS"),

    //点线
    POINT_YYYYMM("yyyy.MM"),
    POINT_YYYYMMdd("yyyy.MM.dd"),
    POINT_YYYYMMddHHmm("yyyy.MM.dd HH:mm"),
    POINT_YYYYMMddHHmmss("yyyy.MM.dd HH:mm:ss"),
    POINT_YYYYMMddHHmmssSSS("yyyy.MM.dd HH:mm:ss:SSS"),




    dd("dd"),
    MM("MM"),
    yyyy("yyyy"),
    yyyyMMdd("yyyyMMdd"),
    yyyyMMddHHmmss("yyyyMMddHHmmss"),
    yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),




    //时间对比
    DURATION_MILLIS("millis"),
    DURATION_ABS_MILLIS("abs_millis"),
    DURATION_SECONDS("seconds"),
    DURATION_ABS_SECONDS("abs_seconds"),
    DURATION_MINUTES("minutes"),
    DURATION_ABS_MINUTES("abs_minutes"),
    DURATION_HOURS("hours"),
    DURATION_ABS_HOURS("abs_hours"),
    DURATION_DAYS("days"),
    DURATION_ABS_DAYS("abs_days");



    private String patterns;


    DateEnums(String patterns) {
        this.patterns = patterns;
    }

    public String getPatterns() {
        return patterns;
    }

}

package com.github.sylphlike.framework.utils.validator;


import com.github.sylphlike.framework.utils.general.DateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class StrValidator {

    /**
     * 英文字母 、数字和下划线
     */
    public final static Pattern GENERAL = PatternPool.GENERAL;
    /**
     * 数字
     */
    public final static Pattern NUMBERS = PatternPool.NUMBERS;
    /**
     * 分组
     */
    public final static Pattern GROUP_VAR = PatternPool.GROUP_VAR;
    /**
     * IP v4
     */
    public final static Pattern IPV4 = PatternPool.IPV4;
    /**
     * IP v6
     */
    public final static Pattern IPV6 = PatternPool.IPV6;
    /**
     * 货币
     */
    public final static Pattern MONEY = PatternPool.MONEY;
    /**
     * 邮件
     */
    public final static Pattern EMAIL = PatternPool.EMAIL;
    /**
     * 移动电话
     */
    public final static Pattern MOBILE = PatternPool.MOBILE;
    /**
     * 身份证号码
     */
    public final static Pattern CITIZEN_ID = PatternPool.CITIZEN_ID;
    /**
     * 邮编
     */
    public final static Pattern ZIP_CODE = PatternPool.ZIP_CODE;
    /**
     * 生日
     */
    public final static Pattern BIRTHDAY = PatternPool.BIRTHDAY;
    /**
     * URL
     */
    public final static Pattern URL = PatternPool.URL;
    /**
     * Http URL
     */
    public final static Pattern URL_HTTP = PatternPool.URL_HTTP;
    /**
     * 中文字、英文字母、数字和下划线
     */
    public final static Pattern GENERAL_WITH_CHINESE = PatternPool.GENERAL_WITH_CHINESE;
    /**
     * UUID
     */
    public final static Pattern UUID = PatternPool.UUID;
    /**
     * 不带横线的UUID
     */
    public final static Pattern UUID_SIMPLE = PatternPool.UUID_SIMPLE;
    /**
     * 中国车牌号码
     */
    public final static Pattern PLATE_NUMBER = PatternPool.PLATE_NUMBER;



    /**
     * 验证是否为生日<br>
     * 只支持以下几种格式：
     * <ul>
     * <li>yyyyMMdd</li>
     * <li>yyyy-MM-dd</li>
     * <li>yyyy/MM/dd</li>
     * <li>yyyy.MM.dd</li>
     * <li>yyyy年MM月dd日</li>
     * </ul>
     *
     * @param value 值
     * @return 是否为生日
     */
    public static boolean isBirthday(CharSequence value) {
        final Matcher matcher = BIRTHDAY.matcher(value);
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(3));
            int day = Integer.parseInt(matcher.group(5));
            return isBirthday(year, month, day);
        }
        return false;
    }


    /**
     * 验证是否为生日
     *
     * @param year  年，从1900年开始计算
     * @param month 月，从1开始计数
     * @param day   日，从1开始计数
     * @return 是否为生日
     */
    public static boolean isBirthday(int year, int month, int day) {
        // 验证年
        int thisYear = DateUtils.thisYear();
        if (year < 1900 || year > thisYear) {
            return false;
        }

        // 验证月
        if (month < 1 || month > 12) {
            return false;
        }

        // 验证日
        if (day < 1 || day > 31) {
            return false;
        }
        // 检查几个特殊月的最大天数
        if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        if (month == 2) {
            // 在2月，非闰年最大28，闰年最大29
            return day < 29 || (day == 29 && DateUtils.isLeapYear(year));
        }
        return true;
    }
}

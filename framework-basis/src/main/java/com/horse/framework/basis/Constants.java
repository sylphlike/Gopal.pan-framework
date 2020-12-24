package com.horse.framework.basis;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 *  系统常量接口
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public interface Constants {

    int DIGITAL_ZERO = 0;
    int DIGITAL_ONE = 1;
    int DIGITAL_TWO = 2;
    int DIGITAL_THREE = 3;
    int DIGITAL_FOUR = 4;
    int DIGITAL_FIVES = 5;
    int DIGITAL_SEVEN = 7;
    int DIGITAL_TEN = 10;
    int DIGITAL_TWENTY_FOUR = 24;
    int DIGITAL_HUNDRED = 100;
    int DIGITAL_TWO_HUNDRED = 200;
    int DIGITAL_THOUSAND = 1000;
    int DIGITAL_TEN_THOUSAND = 10000;
    int DIGITAL_ONE_HUNDRED_THOUSAND = 100000;

    /** 日志链路追踪 */
    String TRACE = "trace";

    /** 登陆授权，角色资源授权 用户携带header名称 */
    String TOKEN = "token";

    /** header 个人用户和企业用户合并使用字段*/
    String IDENTITY_ID = "identityId";





}

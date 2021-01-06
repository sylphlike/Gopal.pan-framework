package com.github.sylphlike.framework.web;

import com.github.sylphlike.framework.norm.RCode;

/**
 * 框架提供的系统类错误码，和已知通用的业务错误码
 * <p>  time 19/11/2020 16:11  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public enum FReply implements RCode {

    //300000 - 399999 系统级别错误码区间
    FW_CONFIG_CONVERSION_               (30001, "转换类型失败"),
    FW_CONFIG_CONVERSION_ENUM           (30002, "转换enum类型失败"),
    FW_CONFIG_NO_ANNOTATION             (30003, "未启用对应注解"),

    //----------------------------------------------------------------------------------------

    //40000 - 49999 业务区间，客户端
    FW_CLIENT_PARAM_PARSE_ERROR         (40001, "参数解析失败/参数缺少"),
    FW_CLIENT_PARAM_MISS                (40002, "参数丢失"),
    FW_CLIENT_PARAM_ERROR               (40003, "参数错误"),
    FW_CLIENT_NO_SUPPORT_METHOD         (40004, "不支持该请求方法"),
    FW_CLIENT_NO_SUPPORT_TYPE           (40005, "不支持当前媒体类型"),
    FW_CLIENT_PARAM_BIND                (40006, "参数绑定失败"),
    FW_CLIENT_PARAM_INVALID             (40007, "参数无效"),
    FW_CLIENT_PARAM_MISMATCH            (40008, "参数类型不匹配"),

    //----------------------------------------------------------------------------------------

    //50000 - 50999 业务区间，用户类


    FW_USER_USER_MISS                   (50001, "用户不存在"),
    FW_USER_USER_NOT_AVAILABLE          (50002, "账户已被禁用"),
    FW_USER_PASSWORD_ERR                (50003, "密码不正确"),
    FW_USER_TOME_OUT                    (50004, "登陆过期"),
    FW_USER_NOT_LOGIN                   (50005, "用户未登陆"),
    FW_USER_FREEZE                      (50006, "您输入的交易密码错误次数累计超过5次，当前账户已被冻结，24小时后自动解冻"),

    //88000 - 88999 业务区间，数据校验类错误码区间
    FW_LOGIC_DATA_ERROR                 (88001, "数据校验失败"),
    FW_DATA_UNAFFECTED_ADD              (88002, "添加数据失败"),
    FW_DATA_UNAFFECTED_UPDATE           (88003, "修改数据失败"),
    FW_DATA_UNAFFECTED_DELETE           (88004, "删除数据失败"),
    FW_DATA_MISS                        (88005, "数据不存在"),
    FW_DATE_DUPLICATE_KEY               (88006, "数据重复"),
    FW_DATA_TRANSACTION                 (88998, "事物执行异常"),
    FW_DATE_UNKNOWN_ERROR               (88999, "数据异常"),

    FW_UNKNOWN_ERROR                    (89999,"未知错误，请稍后重试或联系客服人员"),


    ;

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    FReply(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

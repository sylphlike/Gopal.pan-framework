package com.github.sylphlike.framework.web;

import com.github.sylphlike.framework.norm.RCode;

/**
 * 框架系统类错误码
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </p>
 * @author Gopal.pan
 * @version 1.0.0
 */

public enum FReply implements RCode {

    //300000 - 399999 系统级别错误码区间
    FW_CONFIG_CONVERSION_               ("F00X300001","转换类型失败"),
    FW_CONFIG_CONVERSION_ENUM           ("F00X300002","转换enum类型失败"),
    FW_CONFIG_NO_ANNOTATION             ("F00X300003","未启用对应注解"),



    //40000 - 49999 业务区间，客户端
    FW_CLIENT_PARAM_PARSE_ERROR         ("F00X400001","参数解析失败/参数缺少"),
    FW_CLIENT_PARAM_MISS                ("F00X400002","参数丢失"),
    FW_CLIENT_PARAM_ERROR               ("F00X400003","参数错误"),
    FW_CLIENT_NO_SUPPORT_METHOD         ("F00X400004","不支持该请求方法"),
    FW_CLIENT_NO_SUPPORT_TYPE           ("F00X400005","不支持当前媒体类型"),
    FW_CLIENT_PARAM_BIND                ("F00X400006","参数绑定失败"),
    FW_CLIENT_PARAM_INVALID             ("F00X400007","参数无效"),
    FW_CLIENT_PARAM_MISMATCH            ("F00X400008","参数类型不匹配"),


    //50000 - 50999 业务区间，用户类
    FW_USER_USER_MISS                   ("F00X500001","用户不存在"),
    FW_USER_USER_NOT_AVAILABLE          ("F00X500002","账户已被禁用"),
    FW_USER_PASSWORD_ERR                ("F00X500003","密码不正确"),
    FW_USER_TOME_OUT                    ("F00X500004","登陆过期"),
    FW_USER_NOT_LOGIN                   ("F00X500005","用户未登陆"),
    FW_USER_FREEZE                      ("F00X500006","您输入的交易密码错误次数累计超过5次，当前账户已被冻结，24小时后自动解冻"),

    //88000 - 88999 业务区间，数据校验类错误码区间
    FW_LOGIC_DATA_ERROR                 ("F00X880001","数据校验失败"),
    FW_DATA_UNAFFECTED_ADD              ("F00X880002","添加数据失败"),
    FW_DATA_UNAFFECTED_UPDATE           ("F00X880003","修改数据失败"),
    FW_DATA_UNAFFECTED_DELETE           ("F00X880004","删除数据失败"),
    FW_DATA_MISS                        ("F00X880005","数据不存在"),
    FW_DATE_DUPLICATE_KEY               ("F00X880006","数据重复"),
    FW_DATA_TRANSACTION                 ("F00X889098","事物执行异常"),
    FW_DATE_UNKNOWN_ERROR               ("F00X889099","数据异常"),
    FW_UNKNOWN_ERROR                    ("F00X899099","未知错误，请稍后重试或联系客服人员"),


    ;

    private final String code;
    private final String message;

    public String getCode() { return code;}
    public String getMessage() {
        return message;
    }

    FReply(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.horse.framework.utils.email;

import lombok.Data;

import java.io.Serializable;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
public class EmailBo implements Serializable {
    private static final long serialVersionUID = 51690660207362843L;


    /** smtp 邮件服务地址*/
    private String smtpHost;

    /**
     * 邮件正文格式 {@link EmailContentEnum}
     *   HTML     以HTML样式发送，需传入内容以标准HTML规范编写
     *   TEXT     以文本形式发送
     */
    private String contentType;

    /** 发送方邮件地址 */
    private String sendEmailAccount;

    /** 发送方账户密码 163、qq邮箱使用授权码*/
    private String sendEmailPassword;


    /** 发送方nick 名称*/
    private String sendEmailNickname;

    /** 接收方email地址,多个使用英文逗号分开 */
    private String receiveEmailAccount;


    /** 抄送方email地址,多个使用英文逗号分开 */
    private String carbonCopyAccount;


    /** 密送方email地址,多个使用英文逗号分开 */
    private String blindCarbonCopyAccount;

    /** 邮件主题 */
    private String subject;

    /** 邮件正文内容 */
    private String content;

    /** 邮件正文图片地址 只有在 HTML 内容下有效 */
    private String contentImage;

    /** 附加地址 */
    private String attachmentAddress;
}

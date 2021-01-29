package com.github.sylphlike.framework.glued.domain.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 业务系统接入平台配置
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

@Getter
@Setter
@ToString(callSuper = true)
public class ClientConfig {


    /** 接入平台 */
    private String platform;

    /** 客户端id */
    private String appKey;

    /** 客户端Secret */
    private String appSecret;

    /** 登录成功后的回调地址 */
    private String redirectUri;


    /**
     * 支付宝公钥：当选择支付宝登录时，该值可用
     * 对应“RSA2(SHA256)密钥”中的“支付宝公钥”
     */
    private String alipayPublicKey;


}

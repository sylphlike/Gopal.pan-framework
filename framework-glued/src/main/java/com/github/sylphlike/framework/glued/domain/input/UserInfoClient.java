package com.github.sylphlike.framework.glued.domain.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  用户信息业务参数
 * <p>  time 10/11/2020 10:54  星期二 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserInfoClient extends ClientConfig {

    /** 用户授权的唯一票据 */
    private String accessToken;

    /** accessToken的生命周期，单位是秒数。 */
    private long expireIn;

    private String openId;
}

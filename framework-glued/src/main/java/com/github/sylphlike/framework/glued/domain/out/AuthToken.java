package com.github.sylphlike.framework.glued.domain.out;

import lombok.*;

/**
 * 提供给业务系统的通用 授权token实体类
 * <p>  time 05/11/2020 17:56  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 * @author Gopal.pan
 * @version 1.0.0
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {


    /** 用户授权的唯一票据 */
    private String accessToken;

    /** accessToken的生命周期，单位是秒数。 */
    private long expireIn;

    /** 用户开放ID */
    private String openId;
}

package com.github.sylphlike.framework.glued.domain.out;

import lombok.*;

/**
 * 提供给业务系统的通用 授权token实体类
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
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

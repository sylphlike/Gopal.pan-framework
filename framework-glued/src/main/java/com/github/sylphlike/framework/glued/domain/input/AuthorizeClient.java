package com.github.sylphlike.framework.glued.domain.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Oauth2 的authorize接口配置
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */


@Getter
@Setter
@ToString(callSuper = true)
public class AuthorizeClient extends ClientConfig{

    /** 应用授权范围 */
    private String scope;


}

package com.github.sylphlike.framework.glued.domain.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Oauth2 的authorize接口配置
 * <p>  time 09/11/2020 11:12  星期一 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
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

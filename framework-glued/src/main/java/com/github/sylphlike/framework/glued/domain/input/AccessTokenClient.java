package com.github.sylphlike.framework.glued.domain.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * accessToken 业务参数
 * <p>  time 10/11/2020 10:38  星期二 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AccessTokenClient extends ClientConfig {

    private String code;
}

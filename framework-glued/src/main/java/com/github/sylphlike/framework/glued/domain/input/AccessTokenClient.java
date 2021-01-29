package com.github.sylphlike.framework.glued.domain.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * accessToken 业务参数
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AccessTokenClient extends ClientConfig {

    private String code;
}

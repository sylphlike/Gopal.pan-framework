package com.github.sylphlike.framework.glued.api;


import com.github.sylphlike.framework.glued.domain.input.AccessTokenClient;
import com.github.sylphlike.framework.glued.domain.out.AuthToken;

/**
 *  三方平台回调接口
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public interface CallbackClient {

    AuthToken authorize(AccessTokenClient accessTokenClient);
}

package com.github.sylphlike.framework.glued.api;


import com.github.sylphlike.framework.glued.domain.input.AccessTokenClient;
import com.github.sylphlike.framework.glued.domain.out.AuthToken;

/**
 * <p>  time 10/11/2020 09:24  星期二 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *  三方平台回调接口
 * @author Gopal.pan
 * @version 1.0.0
 */
public interface CallbackClient {

    AuthToken authorize(AccessTokenClient accessTokenClient);
}

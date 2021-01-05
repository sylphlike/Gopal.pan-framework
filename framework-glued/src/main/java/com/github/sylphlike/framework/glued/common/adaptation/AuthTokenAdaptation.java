package com.github.sylphlike.framework.glued.common.adaptation;


import com.github.sylphlike.framework.glued.domain.out.AuthToken;

/**
 * <p>  time 09/11/2020 15:31  星期一 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public class AuthTokenAdaptation {

    public static AuthToken weiboAdaptation(WeiboAuthToken weiboAuthToken){

        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(weiboAuthToken.getAccess_token());
        authToken.setExpireIn(weiboAuthToken.getExpires_in());
        authToken.setOpenId(weiboAuthToken.getUid());

        return authToken;


    }
}

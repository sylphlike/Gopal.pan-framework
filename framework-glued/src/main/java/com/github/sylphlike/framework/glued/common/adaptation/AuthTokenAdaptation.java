package com.github.sylphlike.framework.glued.common.adaptation;


import com.github.sylphlike.framework.glued.domain.out.AuthToken;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
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

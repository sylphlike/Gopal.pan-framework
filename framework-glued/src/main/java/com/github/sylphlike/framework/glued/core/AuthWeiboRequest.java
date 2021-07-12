package com.github.sylphlike.framework.glued.core;


import com.github.sylphlike.framework.adapt.JsonConfig;
import com.github.sylphlike.framework.adapt.cache.UUIDCache;
import com.github.sylphlike.framework.glued.common.adaptation.AuthTokenAdaptation;
import com.github.sylphlike.framework.glued.common.adaptation.UserInfoAdaptation;
import com.github.sylphlike.framework.glued.common.adaptation.WeiboAuthToken;
import com.github.sylphlike.framework.glued.common.adaptation.WeiboUserInfo;
import com.github.sylphlike.framework.glued.common.config.source.AuthSource;
import com.github.sylphlike.framework.glued.common.config.source.DefaultAuthSource;
import com.github.sylphlike.framework.glued.common.exception.GluedException;
import com.github.sylphlike.framework.glued.common.utils.UrlBuilder;
import com.github.sylphlike.framework.glued.domain.input.AccessTokenClient;
import com.github.sylphlike.framework.glued.domain.input.AuthorizeClient;
import com.github.sylphlike.framework.glued.domain.input.ClientConfig;
import com.github.sylphlike.framework.glued.domain.input.UserInfoClient;
import com.github.sylphlike.framework.glued.domain.out.AuthToken;
import com.github.sylphlike.framework.glued.domain.out.UserInfo;
import com.github.sylphlike.framework.norm.CharsetUtil;
import com.github.sylphlike.framework.utils.general.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * <p>  time 17:56 2017/03/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class AuthWeiboRequest extends AbstractAuth {


    public AuthWeiboRequest(ClientConfig config) {
        super(config, DefaultAuthSource.WEIBO);
    }

    @Override
    public String authorize() throws GluedException {
        return authorizeUrl(super.authSource,super.clientConfig);
    }


    @Override
    public AuthToken accessToken() throws Exception {
        String url = accessTokenUrl(super.authSource, super.clientConfig);
        String result = HttpUtils.doPost(url);

        WeiboAuthToken weiboAuthToken = JsonConfig.mapper().readValue(result, WeiboAuthToken.class);

        return AuthTokenAdaptation.weiboAdaptation(weiboAuthToken);
    }


    @Override
    public UserInfo userInfo() throws IOException {
        String url = userInfoUrl(super.authSource, super.clientConfig);
        String result = HttpUtils.doGet(url);
        WeiboUserInfo weiboUserInfo = JsonConfig.mapper().readValue(result, WeiboUserInfo.class);
        return  UserInfoAdaptation.weiboAdaptation(weiboUserInfo);


    }









    public static String authorizeUrl(AuthSource authSource, ClientConfig clientConfig) {
        AuthorizeClient authorizeClient = (AuthorizeClient) clientConfig;
        return new UrlBuilder(authSource.authorize())
                .builderParam("client_id", authorizeClient.getAppKey())
                .builderParam("client_secret", authorizeClient.getAppSecret())
                .builderParam("response_type","code")
                .builderParam("redirect_uri", authorizeClient.getRedirectUri())
                .builderParam("scope", authorizeClient.getScope())
                .builderParam("state", StringUtils.join( UUIDCache.UUID(), CharsetUtil.STRING_ENGLISH_DOT,authSource.getName()))
                .build();
    }

    public static String accessTokenUrl(AuthSource authSource, ClientConfig clientConfig) {
        AccessTokenClient accessTokenClient = (AccessTokenClient)clientConfig;
        return new UrlBuilder(authSource.accessToken())
                .builderParam("client_id", accessTokenClient.getAppKey())
                .builderParam("client_secret", accessTokenClient.getAppSecret())
                .builderParam("grant_type","authorization_code")
                .builderParam("redirect_uri",accessTokenClient.getRedirectUri())
                .builderParam("code",accessTokenClient.getCode())
                .build();
    }


    public static String userInfoUrl(AuthSource authSource, ClientConfig clientConfig) {
        UserInfoClient userInfoClient = (UserInfoClient)clientConfig;

        return new UrlBuilder(authSource.userInfo())
                .builderParam("client_id", userInfoClient.getAppKey())
                .builderParam("client_secret", userInfoClient.getAppSecret())
                .builderParam("redirect_uri",userInfoClient.getRedirectUri())
                .builderParam("access_token",userInfoClient.getAccessToken())
                .builderParam("uid",userInfoClient.getOpenId())
                .build();
    }
}

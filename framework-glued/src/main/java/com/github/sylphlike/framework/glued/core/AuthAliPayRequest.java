package com.github.sylphlike.framework.glued.core;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.github.sylphlike.framework.basis.UUIDCache;
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
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * <p>  time 10/11/2020 14:22  星期二 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public class AuthAliPayRequest  extends AbstractAuth  {

    private final AlipayClient alipayClient;

    public AuthAliPayRequest(ClientConfig clientConfig) {
        super(clientConfig, DefaultAuthSource.ALIPAY);
        this.alipayClient = new DefaultAlipayClient(DefaultAuthSource.ALIPAY.accessToken(), clientConfig.getAppKey(),
                clientConfig.getAppSecret(), "json", "UTF-8", clientConfig.getAlipayPublicKey(), "RSA2");
    }


    @Override
    public String authorize() throws GluedException {
        return authorizeUrl(super.authSource,super.clientConfig);
    }

    @Override
    public AuthToken accessToken() throws Exception {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(((AccessTokenClient) this.clientConfig).getCode());

        AlipaySystemOauthTokenResponse execute = alipayClient.execute(request);

        return AuthToken.builder()
                .accessToken(execute.getAccessToken())
                .openId(execute.getUserId())
                .expireIn(Long.parseLong(execute.getExpiresIn())).build();
    }


    @Override
    public UserInfo userInfo() throws GluedException, IOException, AlipayApiException {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();

        UserInfoClient userInfoClient = (UserInfoClient)clientConfig;

        AlipayUserInfoShareResponse execute = this.alipayClient.execute(request, userInfoClient.getAccessToken());

       return UserInfo.builder()
                .openId(execute.getUserId())
                .nickname(execute.getNickName())
                .gender(execute.getGender())
                .province(execute.getProvince())
                .city(execute.getCity())
                .location(execute.getAddress())
                .avatar(execute.getAvatar())
                .build();

    }



    public static String authorizeUrl(AuthSource authSource, ClientConfig clientConfig) {
        AuthorizeClient authorizeClient = (AuthorizeClient) clientConfig;
        return new UrlBuilder(authSource.authorize())
                .builderParam("app_id", authorizeClient.getAppKey())
                .builderParam("scope", authorizeClient.getScope())
                .builderParam("redirect_uri", authorizeClient.getRedirectUri())
                .builderParam("state", StringUtils.join( UUIDCache.UUID(), CharsetUtil.STRING_ENGLISH_DOT,authSource.getName()))
                .build();
    }
}

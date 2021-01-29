package com.github.sylphlike.framework.glued.core;

import com.alipay.api.AlipayApiException;
import com.github.sylphlike.framework.glued.api.GluedClient;
import com.github.sylphlike.framework.glued.common.config.source.AuthSource;
import com.github.sylphlike.framework.glued.common.exception.GluedException;
import com.github.sylphlike.framework.glued.domain.input.ClientConfig;
import com.github.sylphlike.framework.glued.domain.out.AuthToken;
import com.github.sylphlike.framework.glued.domain.out.UserInfo;

import java.io.IOException;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public abstract class AbstractAuth implements GluedClient {
    protected ClientConfig clientConfig;
    protected AuthSource authSource;

    public AbstractAuth(ClientConfig clientConfig, AuthSource authSource) {
        this.clientConfig = clientConfig;
        this.authSource = authSource;
    }


    @Override
    public String authorize() throws GluedException {
        throw new GluedException(SReply.GLUED_NOT_SUPPORT_OPERATION);
    }

    @Override
    public AuthToken accessToken() throws Exception{
        throw new GluedException(SReply.GLUED_NOT_SUPPORT_OPERATION);
    }


    @Override
    public UserInfo userInfo() throws GluedException, IOException, AlipayApiException {
        throw new GluedException(SReply.GLUED_NOT_SUPPORT_OPERATION);
    }
}

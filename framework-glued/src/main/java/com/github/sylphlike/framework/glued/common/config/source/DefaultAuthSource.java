package com.github.sylphlike.framework.glued.common.config.source;


import com.github.sylphlike.framework.glued.api.GluedClient;
import com.github.sylphlike.framework.glued.core.AuthAliPayRequest;
import com.github.sylphlike.framework.glued.core.AuthWeiboRequest;
import com.github.sylphlike.framework.glued.domain.input.ClientConfig;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */


public enum DefaultAuthSource implements AuthSource {

    WEIBO{
       @Override
       public GluedClient instants(ClientConfig clientConfig) {  return new AuthWeiboRequest(clientConfig);}

       @Override
       public String authorize() { return "https://api.weibo.com/oauth2/authorize"; }

       @Override
       public String accessToken() {  return "https://api.weibo.com/oauth2/access_token"; }

       @Override
       public String userInfo() {  return "https://api.weibo.com/2/users/show.json"; }
   },

   ALIPAY{
      @Override
      public GluedClient instants(ClientConfig clientConfig) {
         return new AuthAliPayRequest(clientConfig);
      }

      @Override
      public String authorize() { return "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm"; }

      @Override
      public String accessToken() {  return "https://openapi.alipay.com/gateway.do";}

      @Override
      public String userInfo() {
         return "https://openapi.alipay.com/gateway.do";
      }
   }


}

package com.github.sylphlike.framework.glued.common.config.source;


import com.github.sylphlike.framework.glued.api.GluedClient;
import com.github.sylphlike.framework.glued.core.AuthAliPayRequest;
import com.github.sylphlike.framework.glued.core.AuthWeiboRequest;
import com.github.sylphlike.framework.glued.domain.input.ClientConfig;

/**
 * <p>  time 05/11/2020 14:58  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
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

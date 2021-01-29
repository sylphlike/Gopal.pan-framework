package com.github.sylphlike.framework.glued.common.adaptation;

import lombok.Data;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
public class WeiboAuthToken {


    //{"access_token":"2.00SYHrbHGD8YEEadf1c7b02cDjFsJE","remind_in":"157679999","expires_in":157679999,"uid":"6972314674","scope":"follow_app_official_microblog","isRealName":"true"}
    private String access_token;
    private String remind_in;
    private long expires_in;
    private String uid;
    private String scope;
    private String isRealName;
}

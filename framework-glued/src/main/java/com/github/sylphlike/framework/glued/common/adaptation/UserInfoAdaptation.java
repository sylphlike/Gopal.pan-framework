package com.github.sylphlike.framework.glued.common.adaptation;


import com.github.sylphlike.framework.glued.domain.out.UserInfo;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class UserInfoAdaptation {
    public static UserInfo weiboAdaptation(WeiboUserInfo weiboUserInfo) {

        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(weiboUserInfo.getIdstr());
        userInfo.setNickname(weiboUserInfo.getScreen_name());
        userInfo.setGender(weiboUserInfo.getGender());
        userInfo.setAvatar(weiboUserInfo.getProfile_image_url());
        userInfo.setProvince(weiboUserInfo.getProvince());
        userInfo.setCity(weiboUserInfo.getCity());
        userInfo.setLocation(weiboUserInfo.getLocation());

        return userInfo;

    }
}

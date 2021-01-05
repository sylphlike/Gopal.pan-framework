package com.github.sylphlike.framework.glued.common.adaptation;


import com.github.sylphlike.framework.glued.domain.out.UserInfo;

/**
 * <p>  time 09/11/2020 16:28  星期一 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
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

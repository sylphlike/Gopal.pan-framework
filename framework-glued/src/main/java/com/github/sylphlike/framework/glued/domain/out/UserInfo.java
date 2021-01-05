package com.github.sylphlike.framework.glued.domain.out;

import lombok.*;

/**
 * <p>  time 09/11/2020 16:13  星期一 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    /** 用户UID */
    private String openId;

    /** 用户昵称 */
    private String nickname;

    /** 性别，m：男、f：女、n：未知 */
    private String gender;

    private String province;

    private String city;

    /** 用户所在地 */
    private String location;

    /** 用户头像地址（中图），50×50像素 */
    private String avatar;

}

package com.github.sylphlike.framework.basis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户请求线程绑定实体类
 * <p>  time 28/01/2021 10:30  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributes {

    /** 用户类型 个人用户 PERSONAL_USER ，企业用户 BUSINESS_USER ，内部员工 INTERNAL_STAFF */
    private String userType;

    private Long userId;

    private String realName;

    private String nickName;
}

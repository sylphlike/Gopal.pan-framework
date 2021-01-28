package com.github.sylphlike.framework.basis;

/**
 * <p>  time 11/01/2021 13:50  星期一 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */
public abstract class UserContextHolder {


    /** 当前登录用户属性 */
    private static final ThreadLocal<UserAttributes> contextUser = new ThreadLocal<>();


    public static void setUserAttributes(UserAttributes attributes) {
        contextUser.set(attributes);
    }

    public static UserAttributes getRequestAttributes() {
        return contextUser.get();
    }


    public static void resetUserAttributes() {
        contextUser.remove();
    }

}

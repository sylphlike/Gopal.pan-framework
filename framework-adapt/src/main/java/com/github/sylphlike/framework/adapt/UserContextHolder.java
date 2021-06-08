package com.github.sylphlike.framework.adapt;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
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

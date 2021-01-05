package com.github.sylphlike.framework.glued.common.config.source;


import com.github.sylphlike.framework.glued.api.GluedClient;
import com.github.sylphlike.framework.glued.domain.input.ClientConfig;

/**
 * 三方应用地址管理
 * <p>  time 05/11/2020 14:54  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public interface AuthSource {


    /**
     * 实例化对应平台的运行实例
     * <p>  time 15:27 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param clientConfig ClientConfig
     * @return  com.github.sylphlike.framework.glued.api.GluedClient
     * @author  Gopal.pan
     */
    GluedClient instants(ClientConfig clientConfig);




    /**
     * 授权api地址
     * <p>  time 15:27 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String authorize();


    /**
     * 获取accessToken的api
     * <p>  time 15:27 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String accessToken();



    /**
     * 获取用户信息
     * <p>  time 15:28 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String userInfo();


    /**
     * 取消授权
     * <p>  time 15:28 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    default String revoke() throws Exception {throw new Exception(""); }




    /**
     *  刷新授权
     * <p>  time 15:28 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    default String refresh() throws Exception {throw new Exception(""); }




    default String getName() {
        if (this instanceof Enum) {
            return String.valueOf(this);
        }
        return this.getClass().getSimpleName();
    }
}

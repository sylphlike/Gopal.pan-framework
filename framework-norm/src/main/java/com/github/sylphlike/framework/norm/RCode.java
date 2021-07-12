package com.github.sylphlike.framework.norm;

/**
 * 返回状态编码接口类
 * <p>  time 17:56 2017/03/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public interface RCode {


    /**
     * 响应码
     * 响应成功编码为 特定值 200000
     * 非成功编码规则，
     *      第一位为大写字母标识为系统类型(团队)。G 网关服务，F framework服务 C 架构组件服务 B 业务系统
     *      第二三为系统标识
     *      第4位为站位符固定值X
     *      五到十位为具体的错误编码
     * <p>  time 16:46 2019/4/23      </p>
     * <p> email 15923508369@163.com  </p>
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String getCode();

    /** 响应结果摘要描述 */
    String getMsg();

    /** 响应结果详细描述 */
    String getSubMsg();




}

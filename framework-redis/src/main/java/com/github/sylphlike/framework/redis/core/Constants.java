package com.github.sylphlike.framework.redis.core;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public interface Constants {

     /** 序列容量 */
     int CAPACITY =  1000;

     /** 序列元数据 */
     String SUFFIX = "_MAX_SERIAL";

     /** 序列容量补充阀值*/
     double PERCENT = 0.2;
}

package com.horse.framework.redis.core;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
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

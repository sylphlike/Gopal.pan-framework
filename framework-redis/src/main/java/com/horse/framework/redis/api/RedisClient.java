package com.horse.framework.redis.api;

import com.horse.framework.redis.core.SerialNumber;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * redis客户端 封装RedisTemplate 提供更丰富的接口属性
 *  当该类中没有提供的方法，调用方可以是用继承方式获取Redis客户端提供的原生方法调用
 *     redisClient.opsForValue();
 *     redisClient.opsForList();
 *     redisClient.opsForSet();
 *     redisClient.opsForZSet();
 *     redisClient.opsForHash();
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Component
@Order(value = 1)
public class RedisClient extends SerialNumber {


}

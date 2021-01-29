package com.github.sylphlike.framework.redis.api;

import com.github.sylphlike.framework.redis.core.SerialNumber;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * redis客户端 封装RedisTemplate 提供更丰富的接口属性
 *
 * <p> 当该类中没有提供的方法，调用方可以是用继承方式获取Redis客户端提供的原生方法调用
 *     redisClient.opsForValue();
 *     redisClient.opsForList();
 *     redisClient.opsForSet();
 *     redisClient.opsForZSet();
 *     redisClient.opsForHash();
 * </p>
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

@Component
@Order(value = 1)
public class RedisClient extends SerialNumber {


}

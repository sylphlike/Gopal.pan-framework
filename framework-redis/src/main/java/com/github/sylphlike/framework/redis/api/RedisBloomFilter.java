package com.github.sylphlike.framework.redis.api;

import com.github.sylphlike.framework.redis.core.BloomFilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * <p>  time 14/10/2020 10:23  星期三 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 *  redis bloom 过滤器API
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Component
public class RedisBloomFilter<T> {

    @Autowired public RedisTemplate<Serializable,Object> redisTemplate;


    /**
     * <p>  time 13:44 2020/10/14 【HH:mm yyyy/MM/dd】  </p>
     * <p> email 15923508369@163.com </p>
     *  布隆过滤器添加值
     * @param bloomFilterHelper
     * @param key
     * @param value
     * @return void
     * @author Gopal.pan
     */
    public  void add(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        int[] offset = bloomFilterHelper.murmurHashOffset(value);

        redisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i : offset) {
                    connection.setBit(key.getBytes(), i, true);
                }
                return null;

            }
        });


    }


    /**
     * <p>  time 13:44 2020/10/14 【HH:mm yyyy/MM/dd】  </p>
     * <p> email 15923508369@163.com </p>
     *  校验过滤器中是否存在对应的值
     * @param bloomFilterHelper
     * @param key
     * @param value
     * @return boolean
     * @author Gopal.pan
     */
    public  boolean include(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        List<Object> offsetResult = redisTemplate.executePipelined(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean flag = true;
                for (int i : offset) {
                    flag = connection.getBit(key.getBytes(), i);
                }
                return flag;
            }
        });
        return !offsetResult.contains(false);
    }

}

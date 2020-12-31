package com.github.sylphlike.framework.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class RedisAccessor {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired public RedisTemplate<Serializable,Object> redisTemplate;


    public ValueOperations<Serializable,Object> opsForValue() {  return this.redisTemplate.opsForValue(); }
    public ListOperations<Serializable,Object> opsForList(){ return this.redisTemplate.opsForList(); }
    public SetOperations<Serializable,Object> opsForSet(){ return this.redisTemplate.opsForSet();}
    public ZSetOperations<Serializable,Object> opsForZSet(){ return redisTemplate.opsForZSet(); }
    public HashOperations<Serializable, Object, Object> opsForHash(){ return redisTemplate.opsForHash(); }




    /**
     * 添加数据
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        try {
            opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LOGGER.error("【common-redis】redis客户端set值时异常, 异常信息 ",e);
        }
        return false;
    }


    /**
     * 添加数据并设置过去时间
     * @param key
     * @param value
     * @param expireSecondTime
     * @return
     */
    public boolean set(final String key, Object value, long expireSecondTime) {
        try {
            opsForValue().set(key, value);
            this.redisTemplate.expire(key, expireSecondTime, TimeUnit.SECONDS);
            return true;

        } catch (Exception e) {
            LOGGER.error("【common-redis】redis客户端set值时异常, 异常信息 ",e);
        }
        return false;
    }


    /**
     * 添加数据并设置过期时间(自定义过期时间单位类型)
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public boolean set(String key, Object value, long expireTime, TimeUnit timeUnit) {
        try {
            opsForValue().set(key, value);
            this.redisTemplate.expire(key, expireTime, timeUnit);
            return true;
        } catch (Exception e) {
            LOGGER.error("【common-redis】redis客户端set值时异常, 异常信息 ",e);
        }
        return false;
    }


    /**
     * 获取值
     * @param key
     * @return
     */
    public Object get(final String key) {
        return opsForValue().get(key);
    }


    /**
     * 获取值
     * @param key
     * @param clazz  需要转换成的实体对象类型
     * @param <T>
     * @return
     */
    public <T> T get(final String key,Class<T> clazz) {
        Object obj = opsForValue().get(key);
        if(StringUtils.isEmpty(obj)){
            return null;
        }
        T t = (T) obj;
        return t;


    }


    /**
     * 订阅发送
     * @param patternTopic
     * @param value
     */
    public void convertAndSend(String patternTopic, String value){
        this.redisTemplate.convertAndSend(patternTopic,value);
    }


    /**
     * 添加数据，如果存在key则不做任何操作
     * @param key
     * @param value
     * @param expireSecondTime
     * @return
     */
    public boolean setNX(String key, Object value, long expireSecondTime) {
        Boolean ifAbsent = opsForValue().setIfAbsent(key, value);
        if(ifAbsent != null && ifAbsent){
            this.redisTemplate.expire(key, expireSecondTime, TimeUnit.MILLISECONDS);
            return true;
        }else{
            return false;
        }
    }


    /**
     * 添加数据，如果存在key则不做任何操作
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean setNX(String key, Object value,long expireTime,TimeUnit timeUnit) {
        Boolean ifAbsent = opsForValue().setIfAbsent(key, value);
        if(ifAbsent != null && ifAbsent){
            this.redisTemplate.expire(key, expireTime, timeUnit);
            return true;
        }else{
            return false;
        }
    }


    /**
     * 是否存在KEY
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);

    }


    /**
     * 删除key
     * @param key
     */
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }


    /**
     * 获取过期时间
     * @param key
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key);
    }


    /**
     * 获取过期时间， 指定时间单位
     * @param key
     * @param timeUnit
     * @return
     */
    public Long getExpire(String key,TimeUnit timeUnit){
        return redisTemplate.getExpire(key,timeUnit);
    }


    /**
     * 设置过期时间
     * @param key
     * @param expireSecondTime
     * @return
     */
    public Boolean expire(String key ,Long expireSecondTime){
        return redisTemplate.expire(key, expireSecondTime, TimeUnit.SECONDS);
    }


    /**
     * 获取所有 以prefixKey 为前缀的所有KEY值
     * @param prefixKey
     * @return
     */
    public Set<Serializable> keys(String prefixKey){
        return redisTemplate.keys(prefixKey);
    }






    /**
     * list 向右侧添加集合数据
     * @param key
     * @param list
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public Long rightPushAll(String key , List<?> list, long expireTime, TimeUnit timeUnit){
        Long rightPushAll = opsForList().rightPushAll(key, list.toArray());
        this.redisTemplate.expire(key,expireTime,timeUnit);
        return rightPushAll;

    }



    /**
     * list 向右侧添加集合数据
     * @param key
     * @param list
     * @return
     */
    public Long rightPushAll(String key , List<?> list){
        return opsForList().rightPushAll(key, list.toArray());

    }



}

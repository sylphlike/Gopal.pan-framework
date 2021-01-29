package com.github.sylphlike.framework.redis.core;

import com.github.sylphlike.framework.redis.api.DistributedReentrantLock;
import com.github.sylphlike.framework.redis.api.RedisClient;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */


@Component
public class SerialCapacityMonitor implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(SerialCapacityMonitor.class);
    @Resource private RedisClient redisClient;

    @Override
    public void run(ApplicationArguments args) {

        Thread thread = new Thread(new TimeoutTimerThread(redisClient));
        thread.start();
    }


    public void realTime(String key){
        logger.info("【framework-utils】全局流水号,同步填充流水号,队列名称[{}]",key);
        String maxKey = StringUtils.join(key,Constants.SUFFIX);
        DistributedReentrantLock lock = new DistributedReentrantLock(redisClient, StringUtils.join(maxKey,"_LOCK"));
        try {
            if(lock.tryLock(1, TimeUnit.MILLISECONDS)){
                redisClient.initSerial(redisClient.get(maxKey, SerialEntity.class));
            }
        } finally {
            lock.unlock();
        }
    }



    class TimeoutTimerThread implements Runnable {

        private final RedisClient redisClient;
        public TimeoutTimerThread(RedisClient redisClient) {
            this.redisClient = redisClient;

        }

        private int  dryRunTimes = 0;

        public void run() {
            try {
                for (;;){
                    ThreadLocalRandom current = ThreadLocalRandom.current();
                    TimeUnit.SECONDS.sleep(current.nextInt(60,90));
                    Set<Serializable> keys = redisClient.redisTemplate.keys(StringUtils.join("*", Constants.SUFFIX));
                    if(ObjectUtils.isEmpty(keys)){
                        dryRunTimes ++;
                        if(dryRunTimes > 16){  //当系统启动后至少运行16分钟后依然没有可以使用的自定义序列，那么结束该线程，释放资源。
                            return;
                        }
                    }else {
                        for (Serializable key : keys) {
                            String maxKey = key.toString();
                            String serialKey = maxKey.replace(Constants.SUFFIX, "");
                            Long cacheSize = redisClient.opsForList().size(serialKey);
                            if(ObjectUtils.isEmpty(cacheSize)){ break;}

                            if((double)cacheSize / Constants.CAPACITY < Constants.PERCENT){
                                logger.info("【framework-utils】全局流水号,当前容量小于0.2,补充队列容量,队列名称[{}]",serialKey);
                                SerialEntity serialEntity = redisClient.get(key.toString(), SerialEntity.class);
                                DistributedReentrantLock lock = new DistributedReentrantLock(redisClient,StringUtils.join(maxKey,"_LOCK"));
                                try {
                                    if(lock.tryLock(1, TimeUnit.MILLISECONDS)){
                                        redisClient.initSerial(serialEntity);
                                    }
                                } finally {
                                    lock.unlock();
                                }

                            }
                        }
                    }

                }
            } catch (Exception ignored) {
                // ignored  忽略抛出的异常，当业务系统未启用Redis时该方法抛出异常后自动停止运行
            }
        }


    }


}

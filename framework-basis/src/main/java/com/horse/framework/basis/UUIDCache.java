package com.horse.framework.basis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 *  缓存UUID提升生成UUID的性能
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class UUIDCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(UUIDCache.class);

    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10000);

    public static String  UUID(){
        String cacheUUID =  queue.poll();
        if(StringUtils.isNotBlank(cacheUUID)){
            return cacheUUID;
        }else {

            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("【framework-basis】获取traceID,队列中缓存数据使用完毕,开启线程填充缓存");
            }
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            new Thread(() -> {
                synchronized (UUIDCache.class){
                    while (true){
                        boolean offer = queue.offer(UUID.randomUUID().toString().replaceAll("-", ""));
                        if (!offer){
                            if (LOGGER.isDebugEnabled()){
                                LOGGER.debug("framework-basis】获取traceID,队列中缓存数据使用完毕,线程填充缓存执行完成");
                            }
                            return;
                        }
                    }
                }

            }).start();

            return uuid;
        }

    }




}

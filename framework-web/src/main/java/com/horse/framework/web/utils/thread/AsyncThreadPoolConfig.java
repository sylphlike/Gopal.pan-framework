package com.horse.framework.web.utils.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */


@Data
@Component
@ConfigurationProperties(prefix = "horse.framework.thread.async")
public class AsyncThreadPoolConfig {

    // 核心线程数
    private int corePoolSize = 5;
    // 最大线程数
    private int maximumPoolSize = 20;
    // 超时时间30秒
    private long keepAliveTime = 30;

    //队列容量
    private int queueCapacity = 500;
}

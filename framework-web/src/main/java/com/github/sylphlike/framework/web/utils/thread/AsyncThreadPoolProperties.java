package com.github.sylphlike.framework.web.utils.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * <p>  time 17:56 2019/10/29  星期五 </p>
 * <p> email 15923508369@163.com     </p>
 * @author Gopal.pan
 * @version 1.0.0
 */


@Data
@Component
@ConfigurationProperties(prefix = "sylphlike.framework.thread.async")
public class AsyncThreadPoolProperties {

    /**核心线程数 */
    private int corePoolSize = 5;
    /** 最大线程数 */
    private int maximumPoolSize = 20;
    /** 超时时间30秒 */
    private long keepAliveTime = 30;

    /** 队列容量*/
    private int queueCapacity = 500;
}

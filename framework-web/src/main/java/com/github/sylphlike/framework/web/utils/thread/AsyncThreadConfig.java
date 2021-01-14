package com.github.sylphlike.framework.web.utils.thread;

import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 线程池配置 基于spring的线程池配置
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */

@EnableAsync
@Configuration
public class AsyncThreadConfig {


    private final AsyncThreadPoolConfig asyncThreadPoolConfig;

    public AsyncThreadConfig(AsyncThreadPoolConfig asyncThreadPoolConfig) {
        this.asyncThreadPoolConfig = asyncThreadPoolConfig;
    }


    @Bean
    @ConditionalOnMissingBean
    public AsyncThreadPoolConfig asyncThreadPoolConfig(){ return new AsyncThreadPoolConfig(); }




    /**
     * 创建异步线程池
     * 使用异步线程池时，指定bean名称
     * 使用eg：
     *  1.定义单独处理业务的service （异步必须重新定义service，否则异步失效，因为异步采用AOP代理模式）
     *  2.在需要处理业务的方法上使用此注解 @Async(SystemConstant.ASYNC_THREAD_POOL_BEAN_NAME)
     *      需要异步处理的业务，可以统一定义一个service，但方法必须命名规范合适；也可以单独定义service进行处理
     *
     * @return 线程池
     */

    @Bean(AsyncDefaultThreadPool.ASYNC_DEFAULT_POOL_BEAN_NAME)
    public Executor asyncThreadPoolExecutor() {
        return getExecutor();
    }



    private Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncThreadPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(asyncThreadPoolConfig.getMaximumPoolSize());
        executor.setQueueCapacity(asyncThreadPoolConfig.getQueueCapacity());
        executor.setThreadNamePrefix("customize default ");
        executor.setTaskDecorator(new ExecutorTaskDecorator());
        //关闭程序时，等待任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //系统柜关闭等待中断时间。此处默认写死，不外部传值
        executor.setAwaitTerminationSeconds(600);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }



    public static class ExecutorTaskDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {

            return () -> {
                try {
                    Map<String, String> contextMap = MDC.getCopyOfContextMap();
                    if (contextMap != null)  MDC.setContextMap(contextMap);
                    ServletRequestAttributes context = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
                    RequestContextHolder.setRequestAttributes(context);

                    runnable.run();
                } finally {
                    MDC.clear();
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        }
    }
}

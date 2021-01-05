package com.github.sylphlike.framework.web.utils.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 *  多线程任务提交
 * <p> 调用方式：
 *          主线程调用
 *             List<CallableTemplate<Boolean>> tasks = new ArrayList<>();
 *             for (int x = 0; x < 21; x ++){
 *                  DownRqCode downRqCode = new DownRqCode(redisClient);
 *                  tasks.add(downRqCode);
 *             }
 *             List<Boolean> downloadResult = concurrentThreadPool.invokeAll(tasks);
 *             //解析处理线程返回结果
 *
 *          业务代码块
 *             static class DownRqCode extends CallableTemplate<Boolean>{
 *                  private RedisClient redisClient;
 *
 *                  public DownRqCode(RedisClient redisClient){
 *                      this.redisClient = redisClient;
 *
 *                  }
 *
 *              @Override
 *              public Boolean process() {
 *                  //业务逻辑
 *                  return true;
 *              }
 *          }
 *
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */

@Component
public class ConcurrentThreadPool {

    private final Logger LOGGER = LoggerFactory.getLogger(ConcurrentThreadPool.class);



    @Autowired private ThreadPoolConfig threadPoolConfig;
    @Autowired private ThreadPoolExecutor  threadPoolExecutor;

    @Bean
    @ConditionalOnMissingBean
    public ThreadPoolConfig threadPoolConfig(){ return new ThreadPoolConfig(); }


    @Bean
    @ConditionalOnMissingBean
    public ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(threadPoolConfig.getCorePoolSize(),  threadPoolConfig.getMaximumPoolSize(), threadPoolConfig.getKeepAliveTime(),
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }

    /**
     * 提交单个任务
     * @param task
     * @param <V>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public   <V> V submit(CallableTemplate<V> task) throws InterruptedException, ExecutionException {
        LOGGER.info("【framework-web】 提交多线程任务 [{}]",task);
        Future<V> result = threadPoolExecutor.submit(task);
        return result.get();
    }


    /**
     * 批量提交多个任务
     * @param tasks
     * @param <V>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public <V> List<V> invokeAll(List<? extends CallableTemplate<V>> tasks)  throws InterruptedException, ExecutionException {

        LOGGER.info("【framework-web】 批量多线程任务 提交任务数量为[{}]",tasks.size());
        List<Future<V>> tasksResult = threadPoolExecutor.invokeAll(tasks);
        LOGGER.info("【framework-web】 批量多线程任务 批量任务执行完成");
        List<V> resultList = new ArrayList<V>();
        for (Future<V> future : tasksResult) {
            resultList.add(future.get());
        }
        return resultList;
    }

}

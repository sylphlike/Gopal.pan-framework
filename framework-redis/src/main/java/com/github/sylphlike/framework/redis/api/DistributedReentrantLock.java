package com.github.sylphlike.framework.redis.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * 基于redis分布式锁接口
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class DistributedReentrantLock implements Lock {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedReentrantLock.class);


    /**
     * 默认锁定时间 （5分钟）
     */
    private final static long DEFAULT_LOCK_EXPIRE_MILLISECOND = 1000 * 60 * 5;
    /**
     * 默认重试间隔时间
     */
    private final static long DEFAULT_RETRY_FREQUENCY_MILLISECOND = 100;


    /** 锁名称*/
    private final String    lockName;
    /**锁定毫秒时间*/
    private final long      lockExpireMillisecond;
    /**重试间隔时间*/
    private final long      retryFrequencyMillisecond;

    /** 随机不重复业务ID*/
    private String          transactionId;

    private final RedisClient redisLockClient;
    private volatile boolean locked;



    public DistributedReentrantLock(RedisClient redisLockClient, String lockName){
        this(redisLockClient, lockName, DEFAULT_LOCK_EXPIRE_MILLISECOND, DEFAULT_RETRY_FREQUENCY_MILLISECOND);
    }

    public DistributedReentrantLock(RedisClient redisLockClient, String lockName, long lockExpireMillisecond, long retryFrequencyMillisecond) {
        this.lockName = lockName;
        this.redisLockClient = redisLockClient;
        this.lockExpireMillisecond = lockExpireMillisecond;
        this.retryFrequencyMillisecond = retryFrequencyMillisecond;
    }



    @Override
    synchronized public void lock() {
        tryLock();
    }


    @Override
    synchronized public boolean tryLock() {
        if(locked){ return false; }
        transactionId = UUID.randomUUID().toString();
        boolean result = redisLockClient.setNX(lockName,transactionId,lockExpireMillisecond);
        if(result){
            locked = true;
            LOGGER.debug("【common-redis】 分布式锁加锁成功, lockName [{}] ,transactionId [{}],lockExpireMillisecond [{}]",lockName,transactionId,lockExpireMillisecond);
        }else {
            LOGGER.info("【common-redis】 分布式锁加锁失败, lockName [{}] ,transactionId [{}],lockExpireMillisecond [{}]",lockName,transactionId,lockExpireMillisecond);
        }
        return result;

    }

    @Override
    synchronized public boolean tryLock(long time, TimeUnit unit) {
        long beginTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - beginTime < unit.toMillis(time)) {
            if (this.tryLock()){
                return true;
            }
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(retryFrequencyMillisecond));
        }
        return false;
    }



    @Override
    synchronized public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }


    @Override
    synchronized public void unlock() {
        if(!locked){ return ;}
        if(transactionId.equals(redisLockClient.get(lockName))){
            redisLockClient.redisTemplate.delete(lockName);
        }

    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}

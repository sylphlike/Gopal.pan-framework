package com.github.sylphlike.framework.redis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public class SerialNumber  extends RedisAccessor{

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Resource private SerialCapacityMonitor serialCapacityMonitor;





    /**
     * 初始化指定规则的流水号
     * <p>  time 18:27 2021/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param prefix         流水号规则前缀
     * @param serialLength   自增流水号长度
     * @param key            流水号在Redis中的key值
     * @param currentSerial  当前流水号已使用的最大值
     * @author   Gopal.pan
     */
    public void initSerial(String prefix,int serialLength,String key,String currentSerial){
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("0".repeat(Math.max(0, serialLength)));

        NumberFormat serialFormat = new DecimalFormat(prefix + stringBuffer.toString());
        long currentSequence;
        if(StringUtils.isEmpty(currentSerial)){
            currentSequence = Long.parseLong(stringBuffer.toString());
        }else {
            currentSequence = Long.parseLong(currentSerial.replace(prefix, ""));
        }
        cacheSerial(key,serialFormat,currentSequence);

        SerialEntity serialEntity = new SerialEntity();
        serialEntity.setPrefix(prefix);
        serialEntity.setSerialLength(serialLength);
        serialEntity.setKey(key);
        serialEntity.setCurrentSerial(serialFormat.format(currentSequence + Constants.CAPACITY));


        opsForValue().set(key + Constants.SUFFIX,serialEntity);
    }





    /**
     * 初始化指定规则的流水号
     * <p>  time 18:27 2021/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param serialEntity  原始流水号实体对象
     * @author   Gopal.pan
     */
    public void initSerial(SerialEntity serialEntity){
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("0".repeat(Math.max(0, serialEntity.getSerialLength())));

        NumberFormat serialFormat = new DecimalFormat(serialEntity.getPrefix() + stringBuffer.toString());
        long currentSequence;
        if(StringUtils.isEmpty(serialEntity.getCurrentSerial())){
            currentSequence = Long.parseLong(stringBuffer.toString());
        }else {
            currentSequence = Long.parseLong(serialEntity.getCurrentSerial().replace(serialEntity.getPrefix(), ""));
        }
        cacheSerial(serialEntity.getKey(),serialFormat,currentSequence);
        serialEntity.setCurrentSerial(serialFormat.format(currentSequence + Constants.CAPACITY));
        opsForValue().set(org.apache.commons.lang3.StringUtils.join( serialEntity.getKey() , Constants.SUFFIX),serialEntity);
    }


    /**
     * <p>  time 18:27 2021/1/29      </p>
     * <p> email 15923508369@163.com  </p>
     * @param key   缓存key
     * @return   java.lang.String
     * @author   Gopal.pan
     */
    public String leftPop(String key){
        String serial =(String) opsForList().leftPop(key);
        if(StringUtils.isEmpty(serial)){
            serialCapacityMonitor.realTime(key);
            return (String) opsForList().leftPop(key);
        }
        return serial;
    }


    private  void cacheSerial(String key, NumberFormat serialFormat, Long currentSequence) {
        List<String> list = new ArrayList<>();
        for (int x = 1; x <= Constants.CAPACITY; x ++){
            list.add(serialFormat.format(currentSequence + x));
        }
        opsForList().rightPushAll(key, list.toArray());


    }



}

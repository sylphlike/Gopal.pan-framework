package com.github.sylphlike.framework.basis.cache;

import java.io.Serializable;


/**
 * 本地缓存实体类
 *
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class CacheEntity implements Serializable {

    private static final long serialVersionUID = -5207454718127383515L;

    /** 值*/
    private Object value;

    /** 保存的时间戳*/
    private long gmtModify;

    /** 过期时间*/
    private int expire;


    public CacheEntity(Object value, long gmtModify, int expire) {
        super();
        this.value = value;
        this.gmtModify = gmtModify;
        this.expire = expire;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}

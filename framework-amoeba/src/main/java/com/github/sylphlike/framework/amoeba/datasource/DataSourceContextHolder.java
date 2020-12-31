package com.github.sylphlike.framework.amoeba.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>  time 04/12/2020 16:24  星期五 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
public class DataSourceContextHolder {


    private static final AtomicLong ATOMIC_LONG = new AtomicLong(10);
    private static final List<String> MASTERS = new ArrayList<>();
    private static final List<String> SLAVES = new ArrayList<>();

    private static final ThreadLocal<DataSourceVal> THREAD_LOCAL = new ThreadLocal<>();

    private static final DataSourceContextHolder holder = new DataSourceContextHolder();

    private DataSourceContextHolder() {
    }

    public static DataSourceContextHolder get() {
        return holder;
    }


    void registerDataSourceKey(String dataSourceKey, boolean master){
        if(master){
            MASTERS.add(dataSourceKey);
        }else {
            SLAVES.add(dataSourceKey);
        }

    }

    /**
     * 设置是否使用从库
     * @param useSlave             是否使用从库
     * @param routedBeforeGetConn  是否在获取链接前设置
     * @return
     */
    DataSourceContextHolder useDataBaseType(boolean useSlave, boolean routedBeforeGetConn) {
        DataSourceVal sourceVal = THREAD_LOCAL.get();
        if (sourceVal == null){
            sourceVal = new DataSourceVal();
        }
        sourceVal.userSlave = useSlave;
        sourceVal.routedBeforeGetConn = routedBeforeGetConn;
        THREAD_LOCAL.set(sourceVal);
        return this;
    }


    /**
     * 根据 ThreadLocal 上下文获取本次线程执行的数据库连接key
     * @return
     */
    String getDataSourceKey(){
        DataSourceVal sourceVal = THREAD_LOCAL.get();
        if(sourceVal == null){
            sourceVal = new DataSourceVal();
            sourceVal.forceMaster = true;
            sourceVal.routedBeforeGetConn = false;
            THREAD_LOCAL.set(sourceVal);
            return MASTERS.get(0);
        }else if (sourceVal.repeatedlyExecute && !sourceVal.routedBeforeGetConn){
            sourceVal.forceMaster = true;
            return MASTERS.get(0);
        }else if (sourceVal.forceMaster || !sourceVal.userSlave ){
            return MASTERS.get(0);
        }else {
            if(SLAVES.size() == 0 ){
                return MASTERS.get(0);
            }else {
                int selectIndex = (int) (ATOMIC_LONG.getAndIncrement() % SLAVES.size());
                return SLAVES.get(selectIndex);
            }

        }

    }




    boolean isForceUseMaster() {
        DataSourceVal sourceVal = THREAD_LOCAL.get();
        if (null == sourceVal){
            return false;
        }
        return sourceVal.forceMaster;
    }




    static void changeThreadLocalVal(boolean repeatedlyExecure,boolean routedBeforeGetConn){
        DataSourceVal sourceVal = THREAD_LOCAL.get();
        if(sourceVal != null){
            if(!sourceVal.forceMaster){
                THREAD_LOCAL.remove();
            }else {
                sourceVal.routedBeforeGetConn = routedBeforeGetConn;
                sourceVal.repeatedlyExecute = repeatedlyExecure;
            }

        }
    }

    static void clearDataSourceType() {
        THREAD_LOCAL.remove();
    }

}

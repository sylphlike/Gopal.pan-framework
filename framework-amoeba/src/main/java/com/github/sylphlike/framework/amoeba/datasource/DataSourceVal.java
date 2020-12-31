package com.github.sylphlike.framework.amoeba.datasource;

import lombok.Data;

/**
 * <p>  time 04/12/2020 16:25  星期五 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
public class DataSourceVal {


    /** 是否使用slave数据库 */
    public boolean  userSlave;

    /** 是否强制使用master数据库 */
    public boolean  forceMaster;


    /** 标记在获取连接时是否执行了路由 {@link DataBasesRoutInterceptor}*/
    public boolean  routedBeforeGetConn;


    /** 在本次业务逻辑（包括此次业务处理中存在事物）中是否多次获取数据库连*/
    public boolean repeatedlyExecute;
}

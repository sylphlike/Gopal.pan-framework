package com.github.sylphlike.framework.amoeba.datasource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * <p>  time 04/12/2020 16:24  星期五 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */


@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
@Configuration
public class DataBasesRoutInterceptor implements Interceptor {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DataBasesRoutInterceptor.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];

        //已指定强制使用
        if(DataSourceContextHolder.get().isForceUseMaster()){
            LOGGER.debug("【framework-amoeba】 开启事物，强制路由到master数据库");
            return invocation.proceed();
        }

        if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
            LOGGER.debug("【framework-amoeba】 执行查询操作，使用slave数据库");
            DataSourceContextHolder.get().useDataBaseType(true,true);

        }else{
            LOGGER.debug("【framework-amoeba】 执行CUD操作，使用master数据库");
            DataSourceContextHolder.get().useDataBaseType(false,true);
        }
        return invocation.proceed();


    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}

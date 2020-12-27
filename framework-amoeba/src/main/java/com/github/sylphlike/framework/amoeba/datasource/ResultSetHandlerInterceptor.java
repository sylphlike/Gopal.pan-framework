package com.github.sylphlike.framework.amoeba.datasource;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.context.annotation.Configuration;

import java.sql.Statement;
import java.util.Properties;

/**
 * <p>  time 04/12/2020 16:25  星期五 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}),
})
@Configuration
public class ResultSetHandlerInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        DataSourceContextHolder.changeThreadLocalVal(true,false);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);

    }

    @Override
    public void setProperties(Properties properties) {}
}

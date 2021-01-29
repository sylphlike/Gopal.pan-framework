package com.github.sylphlike.framework.amoeba.datasource;

import com.github.sylphlike.framework.basis.UserContextHolder;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 数据库路由拦截器
 * <p> 1 指定使用的数据库 ,2 字段自动填充 </p>
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */


@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
@Configuration
public class DataBasesRoutInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBasesRoutInterceptor.class);

    /** 自动填充字段定义 */
    private static final String CREATE_USER = "createUser",UPDATE_USER = "updateUser", CREATE_TIME = "createTime",UPDATE_TIME = "updateTime";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];

        boolean useMaster = DataSourceContextHolder.get().isForceUseMaster();
        LOGGER.debug("【framework-amoeba】强制路由到master数据库[{}]",useMaster);

        if(!useMaster && ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
            LOGGER.debug("【framework-amoeba】 执行查询操作，使用slave数据库");
            DataSourceContextHolder.get().useDataBaseType(true,true);
        }else if(!useMaster){
            LOGGER.debug("【framework-amoeba】 执行CUD操作，使用master数据库");
            DataSourceContextHolder.get().useDataBaseType(false,true);
            autoFill(invocation);
        }else {
            autoFill(invocation);
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




    /**
     * 自动填充操作人属性字段
     * <p> create_user create_time update_user update_time 四个字段
     * <p>  time 10:54 2021/1/11 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param invocation  调用方式
     * @throws  IllegalArgumentException ex
     * @author  Gopal.pan
     */
    private void autoFill(Invocation invocation ) throws IllegalAccessException {
        Object[] objects = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) objects[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        //获取参数
        Object parameter = invocation.getArgs()[1];
        Field[] fields = parameter.getClass().getDeclaredFields();

        for(Field field: fields){
            //注入对应的属性值
            if (field.getName().equals(CREATE_TIME) && SqlCommandType.INSERT.equals(sqlCommandType)){
                field.setAccessible(true);
                field.set(parameter, LocalDateTime.now());
            }
            if (field.getName().equals(CREATE_USER) && SqlCommandType.INSERT.equals(sqlCommandType)){
                field.setAccessible(true);
                field.set(parameter, UserContextHolder.getRequestAttributes().getUserId());
            }

            if (field.getName().equals(UPDATE_TIME) && SqlCommandType.UPDATE.equals(sqlCommandType)){
                field.setAccessible(true);
                field.set(parameter,LocalDateTime.now());
            }
            if (field.getName().equals(UPDATE_USER) && SqlCommandType.UPDATE.equals(sqlCommandType)){
                field.setAccessible(true);
                field.set(parameter, UserContextHolder.getRequestAttributes().getUserId());
            }
        }
    }

}

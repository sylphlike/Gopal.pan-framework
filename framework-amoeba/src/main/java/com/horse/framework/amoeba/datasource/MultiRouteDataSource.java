package com.horse.framework.amoeba.datasource;

import com.horse.framework.amoeba.config.DataSourceInfo;
import com.horse.framework.amoeba.exception.MybatisException;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>  time 04/12/2020 16:25  星期五 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Configuration
@Primary
public class MultiRouteDataSource  extends AbstractDataSource implements ApplicationContextAware, InitializingBean, EnvironmentAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiRouteDataSource.class);

    private Environment environment;

    private ApplicationContext context;
    private final Map<Object, DataSource> targetDataSources = new HashMap<>();
    private DataSource defaultDataSource;

    private final String MASTER = "MASTER";

    private final String SLAVE = "SLAVE";


    /**
     * 重写此方法将多个数据源注册到容器中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, DataSourceInfo> dataSourceInfoMap = new HashMap<>();

        Binder binder = Binder.get(environment);
        String drive = binder.bind("horse.datasource.drive-class-name", Bindable.of(String.class)).get();
        //初始化主数据源
        initMasterDataSource(environment ,dataSourceInfoMap,drive);
        //初始化slave数据源
        initSlaveDataSources(environment,dataSourceInfoMap,drive);

        registerDataSources(dataSourceInfoMap);
    }



    private void initMasterDataSource(Environment environment, Map<String, DataSourceInfo> map, String drive) throws MybatisException {
        Binder binder = Binder.get(environment);
        DataSourceInfo.MasterDataSource dataSource = binder.bind("horse.datasource.master-data-source", Bindable.of(DataSourceInfo.MasterDataSource.class)).get();

        if(StringUtils.isEmpty(dataSource.getName())){ throw new MybatisException("未配置主数据源"); }

        DataSourceInfo dataSourceInfo = new DataSourceInfo();
        dataSourceInfo.setMaster(true);
        dataSourceInfo.setDriveClassName(drive);
        DataSourceInfo.MasterDataSource masterDataSource = dataSourceInfo.getMasterDataSource();
        masterDataSource.setName(dataSource.getName());
        masterDataSource.setJdbcUrl(dataSource.getJdbcUrl());
        masterDataSource.setUserName(dataSource.getUserName());
        masterDataSource.setPassword(dataSource.getPassword());

        map.put(MASTER ,dataSourceInfo);
    }


    private void initSlaveDataSources(Environment environment, Map<String, DataSourceInfo> map, String drive) throws MybatisException {
        Binder binder = Binder.get(environment);
        DataSourceInfo.MultipleDataSource dataSource = binder.bind("horse.datasource.multiple-data-source", Bindable.of(DataSourceInfo.MultipleDataSource.class)).get();
        if(ObjectUtils.isEmpty(dataSource)){  throw new MybatisException("未配置slave数据源"); }

        DataSourceInfo dataSourceInfo = new DataSourceInfo();
        dataSourceInfo.setMaster(false);
        dataSourceInfo.setDriveClassName(drive);
        DataSourceInfo.MultipleDataSource multipleDataSource = dataSourceInfo.getMultipleDataSource();
        multipleDataSource.setName(dataSource.getName());
        multipleDataSource.setJdbcUrl(dataSource.getJdbcUrl());
        multipleDataSource.setUserName(dataSource.getUserName());
        multipleDataSource.setPassword(dataSource.getPassword());

        map.put(SLAVE,dataSourceInfo);

    }



    private void registerDataSources(Map<String, DataSourceInfo> dataSourceInfoMap) throws MybatisException {
        if(dataSourceInfoMap.isEmpty()){ throw new MybatisException("未找到数据库配置信息"); }
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) this.context.getAutowireCapableBeanFactory();

        //master链接信息
        DataSourceInfo masterInfo = dataSourceInfoMap.get(MASTER);
        BeanDefinitionBuilder masterBuilder = BeanDefinitionBuilder.genericBeanDefinition(HikariDataSource.class);
        masterBuilder.addPropertyValue("driverClassName",masterInfo.getDriveClassName());
        masterBuilder.addPropertyValue("jdbcUrl",masterInfo.getMasterDataSource().getJdbcUrl());
        masterBuilder.addPropertyValue("username",masterInfo.getMasterDataSource().getUserName());
        masterBuilder.addPropertyValue("password",masterInfo.getMasterDataSource().getPassword());


        register(MASTER,masterBuilder,beanFactory);


        //slave链接信息
        DataSourceInfo slaveInfo = dataSourceInfoMap.get(SLAVE);
        List<String> name = slaveInfo.getMultipleDataSource().getName();
        for (int i = 0; i < name.size(); i++) {
            BeanDefinitionBuilder slaveBuilder = BeanDefinitionBuilder.genericBeanDefinition(HikariDataSource.class);
            slaveBuilder.addPropertyValue("driverClassName",slaveInfo.getDriveClassName());
            slaveBuilder.addPropertyValue("jdbcUrl",slaveInfo.getMultipleDataSource().getJdbcUrl().get(i));
            slaveBuilder.addPropertyValue("username",slaveInfo.getMultipleDataSource().getUserName().get(i));
            slaveBuilder.addPropertyValue("password",slaveInfo.getMultipleDataSource().getPassword().get(i));
            register(name.get(i),slaveBuilder,beanFactory);
        }


    }

    private void register(String beanName, BeanDefinitionBuilder beanDefinitionBuilder, DefaultListableBeanFactory beanFactory) {
        beanFactory.registerBeanDefinition(beanName,beanDefinitionBuilder.getRawBeanDefinition());

        HikariDataSource druidDataSource = (HikariDataSource)this.context.getBean(beanName);
        targetDataSources.put(beanName,druidDataSource);

        boolean master = beanName.equals(MASTER);
        if(master){ defaultDataSource = druidDataSource; }

        DataSourceContextHolder.get().registerDataSourceKey(beanName,master);
    }


    /**
     * 获取执行数据库连接信息
     * @return   DataSource
     */
    private DataSource determineTargetDataSource(){
        Object lookupKey = DataSourceContextHolder.get().getDataSourceKey();
        if(lookupKey == null){
            return defaultDataSource;
        }
        DataSource dataSource = targetDataSources.get(lookupKey);
        if (dataSource == null) {
            throw new MybatisException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        }
        return dataSource;
    }


    @Override
    public Connection getConnection() throws SQLException {
        return determineTargetDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineTargetDataSource().getConnection(username, password);
    }






    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

    }
}

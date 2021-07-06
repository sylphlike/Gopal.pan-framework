package com.github.sylphlike.framework.storage.core;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;


/**
 * @create: Created by intelliJIDEA
 * @author: Gopan
 * @e-mail: 15923508369@163.com
 * @gmdate: 04/05/2020 14:30  星期一 (dd/MM/YYYY HH:mm)
 * @sidesc: 注册枚举转换类
 */
@Component
public class ConfigurationExtend implements ConfigurationCustomizer {


    Logger logger = LoggerFactory.getLogger(ConfigurationExtend.class);

    @Override
    public void customize(MybatisConfiguration configuration) {
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        try {
            final List<Class<?>> allAssignedClass = EnumsPathProvider.getAllAssignedClass(ModelEnum.class);
            allAssignedClass.forEach((clazz) ->{
                logger.info("【framework-storage】 mybatis枚举映射[{}]" , clazz);
                typeHandlerRegistry.register(clazz, GeneralTypeHandler.class);
            });


            //分页插件
            Properties properties = new Properties();
            properties.setProperty("helperDialect", "mysql");
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("returnPageInfo", "check");
            properties.setProperty("params", "count=countSql");
            PageInterceptor pageInterceptor = new PageInterceptor();
            pageInterceptor.setProperties(properties);

            configuration.addInterceptor(pageInterceptor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}

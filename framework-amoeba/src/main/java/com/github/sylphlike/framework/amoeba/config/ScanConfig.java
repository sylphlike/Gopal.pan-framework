package com.github.sylphlike.framework.amoeba.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p>  time 17:56 2019/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Configuration
public class ScanConfig implements EnvironmentAware {


    private String basePackage;


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configure = new MapperScannerConfigurer();
        configure.setBasePackage(basePackage);
        return configure;
    }

    @Override
    public void setEnvironment(Environment environment) {
        Binder binder = Binder.get(environment);
        this.basePackage = binder.bind("sylphlike.mybatis.mapper-base-package", String.class).get();
    }
}

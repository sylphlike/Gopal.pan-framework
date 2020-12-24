package com.horse.framework.amoeba;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p>  time 07/12/2020 16:55  星期一 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
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
        this.basePackage = binder.bind("horse.mybatis.mapper-base-package", String.class).get();
    }
}

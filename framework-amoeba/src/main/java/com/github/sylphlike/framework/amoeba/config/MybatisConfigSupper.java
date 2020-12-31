package com.github.sylphlike.framework.amoeba.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>  time 04/12/2020 16:21  星期五 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "horse.mybatis")
public class MybatisConfigSupper {

    private String mapperBasePackage;

    private String typeAliasesPackage;

    private String mapperLocations;
}

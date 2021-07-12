package com.github.sylphlike.framework.amoeba.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>  time 17:56 2019/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "sylphlike.mybatis")
public class MybatisConfigSupper {

    private String mapperBasePackage;

    private String typeAliasesPackage;

    private String mapperLocations;
}

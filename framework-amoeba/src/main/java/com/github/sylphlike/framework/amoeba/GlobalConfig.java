package com.github.sylphlike.framework.amoeba;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p>  time 07/12/2020 17:14  星期一 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
@Configuration
public class GlobalConfig  extends org.apache.ibatis.session.Configuration implements EnvironmentAware {


    @Override
    public void setEnvironment(Environment environment) {
        super.setMapUnderscoreToCamelCase(true);
        super.setCacheEnabled(true);
    }
}

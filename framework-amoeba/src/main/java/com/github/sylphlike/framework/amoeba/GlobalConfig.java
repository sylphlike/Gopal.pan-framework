package com.github.sylphlike.framework.amoeba;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
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

package com.github.sylphlike.framework.boxing.ons.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>  time 17:56 2019/03/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "sylphlike.framework.ons")
public class ONSProperties {
    private boolean enable;
    private String namesrvAddr;
    private String accesskey;
    private String secretKey;
    private String groupId;
}

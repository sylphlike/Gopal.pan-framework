package com.github.sylphlike.framework.boxing.ons.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "horse.framework.ons")
public class ONSProperties {
    private boolean enable;
    private String namesrvAddr;
    private String accesskey;
    private String secretKey;
    private String groupId;
}

package com.github.sylphlike.framework.adapt.colony;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>  time 10:27 2021/06/07  星期一 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "sylphlike.framework.colony")
public class ColonyProperties {

    /** 订阅的服务名称列表 */
    private List<String> serverNames;

    /** 组名 */
    private String groupName;
}

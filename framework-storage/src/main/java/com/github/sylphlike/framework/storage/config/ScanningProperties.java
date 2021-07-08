package com.github.sylphlike.framework.storage.config;

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
@ConfigurationProperties(prefix = "sylphlike.framework.storage")
public class ScanningProperties {


    /** 数据库实体对应枚举类包路径 */
    private String enumPackage;
}

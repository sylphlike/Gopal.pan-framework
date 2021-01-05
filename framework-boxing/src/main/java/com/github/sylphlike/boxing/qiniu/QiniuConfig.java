package com.github.sylphlike.boxing.qiniu;

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
@ConfigurationProperties(prefix = "horse.framework.qiniu")
public class QiniuConfig {

    /**是否启用 */
    private boolean enable;

    private String accessKey;
    private String secretKey;

    /** 空间名称*/
    private String bucket;

    /** 域名 */
    private String domain;
    /**本地临时根目录 */
    private String localTemp;

}

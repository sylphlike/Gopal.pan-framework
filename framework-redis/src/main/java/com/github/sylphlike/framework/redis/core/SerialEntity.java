package com.github.sylphlike.framework.redis.core;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */


@Data
public class SerialEntity  implements Serializable {

    private static final long serialVersionUID = -8073416331189462055L;

    /** 缓存key */
    private String key;

    /** 流水号前缀标识 */
    private String prefix;

    /** 自动生成流水号的长度 */
    private int serialLength;

    /** 起始流水号 */
    private String currentSerial;
}

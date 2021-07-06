package com.github.sylphlike.framework.norm;

import java.io.Serializable;

/**
 * 持久层枚举字段映射
 * <p>  time 14:10 2021/07/02  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public interface StorageInterface<E extends Enum<E>,T> extends Serializable {


    /** 持久层映射值 */
    T code();

    /** 显示信息 */
    String desc();
}

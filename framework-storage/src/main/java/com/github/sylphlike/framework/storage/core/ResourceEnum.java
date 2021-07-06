package com.github.sylphlike.framework.storage.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * <p>  time 19:28 2020/07/05  星期一 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@AllArgsConstructor
@Getter
public enum  ResourceEnum {
    FILE("file"),
    JAR("jar")

    ;


    private String code;
}

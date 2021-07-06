package com.github.sylphlike.framework.storage.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @create: Created by intelliJIDEA
 * @author: Gopan
 * @e-mail: 15923508369@163.com
 * @gmdate: 27/05/2020 19:13  星期三 (dd/MM/YYYY HH:mm)
 * @sidesc:
 */

@AllArgsConstructor
@Getter
public enum  ResourceEnum {
    FILE("file"),
    JAR("jar")

    ;


    private String code;
}

package com.horse.framework.utils.excel.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 导出实体字段属性注解，如果导出实体属性没有启用改注解，那么该字段将不会被导出
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelModelProperty {


    /**
     * 对应excel文件的标题列文字
     * @return
     */
    String name() default "";

    /**
     * 单元格宽度 可以为每一列设置不同宽度，也可以设置成相同宽度
     * 设置成相同宽度时该注解只用设置一个值，设置不同宽度时应为每一列设置值
     * @return
     */
    int columnWidth() default 4000;

    /**
     * 时间类型格式化
     * @return 格式化的日期字符串形式
     */
    String dateFormat() default "";


    /**
     * 枚举类型格式化
     * @return
     */
    Class enumFormat() default void.class;

    /**
     * 枚举类型格式化对应的属性值
     * @return
     */
    String[] enumFormatAttributes() default {"getCode","getDescription"};


    /**
     * 枚举类型字段是否转换对应说明
     * @return
     */
    boolean enumTransform() default false;
}

package com.github.sylphlike.framework.utils.excel.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 导出实体字段属性注解，如果导出实体属性没有启用改注解，那么该字段将不会被导出
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelModelProperty {

    /**
     * excel文件的标题列文字
     * <p>  time 11:23 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String name() default "";



    /**
     * 单元格宽度 可以为每一列设置不同宽度，也可以设置成相同宽度
     * <p>设置成相同宽度时该注解只用设置一个值，设置不同宽度时应为每一列设置值
     * <p>  time 11:24 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  int
     * @author  Gopal.pan
     */
    int columnWidth() default 4000;

    /**
     * 时间类型格式化
     * <p>  time 11:24 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String dateFormat() default "";




    /**
     * 枚举类型格式化
     * <p>  time 11:24 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.Class
     * @author  Gopal.pan
     */
    Class<?> enumFormat() default void.class;


    /**
     * 枚举类型格式化对应的属性值
     * <p>  time 11:25 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String[]
     * @author  Gopal.pan
     */
    String[] enumFormatAttributes() default {"getCode","getDescription"};



    /**
     * 枚举类型字段是否转换对应说明
     * <p>  time 11:25 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  boolean
     * @author  Gopal.pan
     */
    boolean enumTransform() default false;
}

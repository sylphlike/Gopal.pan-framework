package com.github.sylphlike.framework.utils.excel.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcelModel {


    /**
     * 标题信息
     * @return
     */
    String title() default "";


    /**
     * 导出文件样式
     * @return
     */
    Class  documentStyles() default ExportDefaultStyle.class;

    /**
     * 标题行高
     * @return
     */
    short  titleHeight() default 520;


    /**
     * 表头行高
     * @return
     */
    short headerHeight() default 400;

    /**
     * 内容行高
     * @return
     */
    short contentHeight() default 350;


    /**
     * sheet名称,如果有多个sheet页那么sheet名称格式为 sheetName_index,
     * index以0 开始
     * @return
     */
    String sheetName() default "";

}

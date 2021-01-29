package com.github.sylphlike.framework.utils.excel.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcelModel {

    /**
     * 标题信息
     * <p>  time 11:30 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String title() default "";


    /**
     * 导出文件样式
     * <p>  time 11:30 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.Class
     * @author  Gopal.pan
     */
    Class<?>  documentStyles() default ExportDefaultStyle.class;



    /**
     * 标题行高
     * <p>  time 11:30 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  short
     */
    short  titleHeight() default 520;




    /**
     * 表头行高
     * <p>  time 11:30 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  short
     * @author  Gopal.pan
     */
    short headerHeight() default 400;


    /**
     * 内容行高
     * <p>  time 11:30 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  short
     * @author  Gopal.pan
     */
    short contentHeight() default 350;



    /**
     * sheet名称
     * <p>如果有多个sheet页那么sheet名称格式为 sheetName_index,index以0 开始
     * <p>  time 11:29 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @return  java.lang.String
     * @author  Gopal.pan
     */
    String sheetName() default "";

}

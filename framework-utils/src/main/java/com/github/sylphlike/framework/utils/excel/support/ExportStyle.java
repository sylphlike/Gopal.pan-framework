package com.github.sylphlike.framework.utils.excel.support;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;


/**
 * 导出Excel样式接口
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */

public interface ExportStyle{


    /**
     * 标题样式
     * <p>  time 11:22 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param cellStyle  CellStyle
     * @param font       Font
     * @return  org.apache.poi.ss.usermodel.CellStyle
     * @author  Gopal.pan
     */
    CellStyle titleStyle(CellStyle cellStyle, Font font);




    /**
     * 列表头样式
     * <p>  time 11:22 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param cellStyle  CellStyle
     * @param font       Font
     * @return  org.apache.poi.ss.usermodel.CellStyle
     * @author  Gopal.pan
     */
    CellStyle headerStyle(CellStyle cellStyle, Font font);



    /**
     * 内容样式
     * <p>  time 11:22 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param cellStyle  CellStyle
     * @param font       Font
     * @return  org.apache.poi.ss.usermodel.CellStyle
     * @author  Gopal.pan
     */
    CellStyle contentStyle(CellStyle cellStyle, Font font);



}

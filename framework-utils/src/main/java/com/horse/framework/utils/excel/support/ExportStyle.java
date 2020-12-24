package com.horse.framework.utils.excel.support;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 导出Excel样式接口
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public interface ExportStyle{



    /**
     * 标题样式
     * @param cellStyle
     * @param font
     * @return
     */
    CellStyle titleStyle(CellStyle cellStyle, Font font);


    /**
     * 列表头样式
     * @param cellStyle
     * @param font
     * @return
     */
    CellStyle headerStyle(CellStyle cellStyle, Font font);


    
    /**
     * 内容样式
     * @param cellStyle
     * @param font
     * @return
     */
    CellStyle contentStyle(CellStyle cellStyle, Font font);



}

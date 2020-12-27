package com.github.sylphlike.framework.utils.excel.core;


import com.github.sylphlike.framework.utils.excel.support.ExportStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 默认导出Excel样式
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class ExportDefaultStyle implements ExportStyle {

    @Override
    public CellStyle titleStyle(CellStyle cellStyle, Font font) {
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE_GREY.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        font.setFontName("华文新魏");
        font.setFontHeight((short)390);
        font.setBold(true);
        font.setColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFont(font);
        return  cellStyle;
    }

    @Override
    public CellStyle headerStyle(CellStyle cellStyle, Font font) {
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        font.setFontName("Arial");
        font.setFontHeight((short)250);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFont(font);
        return  cellStyle;
    }

    @Override
    public CellStyle contentStyle(CellStyle cellStyle, Font font) {


        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        font.setFontName("Arial");
        font.setFontHeight((short)230);
        font.setColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFont(font);

        return  cellStyle;
    }
}

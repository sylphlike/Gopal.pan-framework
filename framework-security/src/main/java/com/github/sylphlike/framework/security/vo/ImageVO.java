package com.github.sylphlike.framework.security.vo;

import lombok.Data;

/**
 * <p>  time 23/10/2020 17:12  星期五 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
public class ImageVO {

    /** 图片字节数组*/
    private byte[] imageByte;

    /** 图片中文字 */
    private String text;
}

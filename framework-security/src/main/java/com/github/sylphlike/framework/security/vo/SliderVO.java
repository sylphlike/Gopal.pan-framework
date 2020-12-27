package com.github.sylphlike.framework.security.vo;

import lombok.Data;

/**
 * <p>  time 23/10/2020 15:35  星期五 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
public class SliderVO {

    /** 小图字节数组 */
    private byte[] smallImageByte;

    /** 大图字节数组*/
    private byte[] bigImageByte;

    /** 小图X轴距离 */
    private int xDistance;
}

package com.github.sylphlike.framework.web.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 通用分页入参
 * <p>  time 21/09/2020 11:18  星期一 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageBO  implements Serializable {

    private static final long serialVersionUID = 8578883308040558370L;

    @ApiModelProperty( value = "是否统计总数")
    @Builder.Default
    private  boolean count = true;


    @ApiModelProperty( value = "当前页码")
    @Builder.Default
    private int pageNum  = 1;

    @ApiModelProperty( value = "每页显示条数")
    @Builder.Default
    private int pageSize = 20;
}

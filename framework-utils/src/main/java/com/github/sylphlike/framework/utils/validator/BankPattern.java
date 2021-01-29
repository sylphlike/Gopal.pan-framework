package com.github.sylphlike.framework.utils.validator;

import lombok.Builder;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
@Builder
public class BankPattern {
    private Pattern reg;
    private String typeCode;
    private String typeName;

}

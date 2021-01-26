package com.github.sylphlike.framework.utils.validator;

import lombok.Builder;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * <p>  time 26/01/2021 16:16  星期二 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
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

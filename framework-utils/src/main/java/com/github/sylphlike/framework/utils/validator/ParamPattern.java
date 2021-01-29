package com.github.sylphlike.framework.utils.validator;

/**
 * 参数检验
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */

public interface ParamPattern {

    /** 特殊符号 */
    String SPECIAL_CHARACTER_VALIDATE_WEAK = "^[^`~!@#$%^&*=|{}''//[//].<>/?~！@#￥%……&*——|{}‘；：”“’]*$";

    String SPECIAL_CHARACTER_VALIDATE_MIN = "^[^`~!@#$%^&*+=|{}':;',//[//].<>/?~！@#￥%……&*——+|{}【】‘；：”“’。，、？]*$";

    String SPECIAL_CHARACTER_VALIDATE_FORCED = "^[^`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]*$";





    /** 数字 */
    String ONLY_DIGITAL = "^[0-9]*$";




    String MESSAGE_NOT_SPECIAL = "不能有特殊符号";
    String MESSAGE_ONLY_DIGITAL = "只能是数字";
}

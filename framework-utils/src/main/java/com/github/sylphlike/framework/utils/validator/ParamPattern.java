package com.github.sylphlike.framework.utils.validator;

/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 参数检验
 *
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

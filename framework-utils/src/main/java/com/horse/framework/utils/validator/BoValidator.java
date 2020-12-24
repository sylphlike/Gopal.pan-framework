package com.horse.framework.utils.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 业务数据校验工具类
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class BoValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    /**
     * 校验实体
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String validateEntity(T obj) {
        StringBuilder errorMsg = new StringBuilder();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() != 0) {
            for (ConstraintViolation<T> cv : set) {
                errorMsg.append(cv.getMessage()).append("、");
            }
            String errorString = errorMsg.toString();
            return errorString.substring(0,errorString.length() -1);
        }
        return null;
    }

    /**
     * 校验实体中指定的字段
     * @param obj
     * @param propertyName
     * @param <T>
     * @return
     */
    public static <T> String validateProperty(T obj, String propertyName) {
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        StringBuilder stringBuilder = new StringBuilder();
        if (set != null && set.size() != 0) {
            for (ConstraintViolation<T> cv : set) {
                stringBuilder.append(cv.getMessage()).append("<br/>");
            }
            String errorString = stringBuilder.toString();
            return errorString.substring(0,errorString.length() -1);
        }
        return null;
    }
}

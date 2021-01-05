package com.github.sylphlike.framework.utils.general;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class EnumUtils {

    /**
     * 根据反射，通过方法名称获取方法值，忽略大小写的
     * <p>  time 11:06 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param methodName  方法名称
     * @param obj         对象
     * @param args        参数
     * @return  java.lang.Object
     * @author  Gopal.pan
     */
    private static <T> Object getMethodValue(String methodName, T obj, Object... args) {
        try {
            Method method = obj.getClass().getDeclaredMethod(methodName);
            return  method.invoke(obj, args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过code值获取对应的描述信息
     * <p>  time 11:05 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param code        code
     * @param enumT       枚举类
     * @param methodNames 方法名称
     * @return  java.lang.Object
     * @author  Gopal.pan
     */
    public static <T> Object getDescriptionByByCode(Object code, Class<T> enumT, String... methodNames) {
        for (T obj: enumT.getEnumConstants()) {
            Object resultValue = getMethodValue(methodNames[0], obj);
            assert resultValue != null;
            if (resultValue.toString().equals(code + "")) {
                return getMethodValue(methodNames[1], obj);
            }
        }

        return "";
    }






    /**
     * 枚举转map结合code 作为map的key,description作为map的value
     * <p>  time 11:04 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param enumT       枚举类
     * @param methodNames 方法名称
     * @return  java.util.Map
     * @author  Gopal.pan
     */
    public static <T> Map<Object, String> EnumToMap(Class<T> enumT, String... methodNames) {
        Map<Object, String> enumMap = new HashMap<>();
        if (!enumT.isEnum()) {
            return enumMap;
        }
        T[] enums = enumT.getEnumConstants();

        for (T obj : enums) {
            try {
                Object resultValue = getMethodValue(methodNames[0], obj);
                if ("".equals(resultValue)) {
                    continue;
                }
                Object resultDes = getMethodValue(methodNames[1], obj);
                if ("".equals(resultDes)) {
                    resultDes = obj;
                }
                enumMap.put(resultValue, resultDes + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return enumMap;
    }




}

package com.github.sylphlike.framework.utils.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class BeanMapUtils {
    private static final Logger LOGGER =  LoggerFactory.getLogger(BeanMapUtils.class);


    /**
     * 对象转map
     * <p>  time 11:18 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param obj   待转的实体对象
     * @return  java.util.Map
     * @author  Gopal.pan
     */
    public static Map<String, Object> beanTrans2Map(Object obj) {

        if (null == obj){
            return null;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Map<String, Object> map = new HashMap<>();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }
            }
            return map;
        } catch (Exception e) {
            LOGGER.error("【framework-utils】对象转map发生异常",e);
        }
        return null;
    }

    /**
     * map 转Java bean
     * <p>  time 11:18 2021/1/5 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param map         Map对象
     * @param beanClass   实体对象
     * @return  T         转换后的实体对象
     * @author  Gopal.pan
     */
    public static  <T> T mapTransBean(Map<String, String> map, Class<T> beanClass) {

        try{
            if (map == null){
                return null;
            }
            T obj = beanClass.getDeclaredConstructor().newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
            return obj;
        }catch (Exception e){
            LOGGER.error("【framework-utils】maps转对象时发生异常",e);
        }

        return null;
    }
}

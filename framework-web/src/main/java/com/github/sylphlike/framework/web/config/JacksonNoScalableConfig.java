package com.github.sylphlike.framework.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sylphlike.framework.adapt.JsonConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * 全局Jackson格式化配置，一旦定义后全局不可修改,参数使用个性化@JsonFormat 配置无效
 * <p>LocalDateTime 序列化配置全局配置 全局格式为 yyyy-MM-dd HH:mm:ss
 *    LocalDate     序列化配置全局配置 全局格式为 yyyy-MM-dd
 *    LocalTime     序列化配置全局配置 全局格式为 HH:mm:ss
 *    Long类型转换成 String类型
 * </p>
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
@Configuration
public class JacksonNoScalableConfig {


    @Bean(name = "mapper")
    @Primary
    public ObjectMapper serializingObjectMapper() {

       return JsonConfig.mapper();
    }


}
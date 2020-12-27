package com.github.sylphlike.framework.web.config;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * 全局Jackson格式化配置，可扩展
 *   LocalDateTime 序列化配置全局配置 全局格式为 yyyy-MM-dd HH:mm:ss 参数使用个性化@JsonFormat 配置无效
 *   Long类型转换成 String类型
 *          日期类型处理(参数类型为JSON时需要指定JsonFormat)
 *              支持Date,LocalDateTime
 *              入参为JSON数据格式时需要增加 @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")自定义格式化样式
 *              入参为form表单提交时需要增加  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  自定义格式
 *          Long类型处理
 *              当Long类型数据过长，超过了前端JS数字的最大范围时前端显示异常，将Long类型序列化为String类型
 * @author Gopal.pan
 * @version 1.0.0
 */
/*@Configuration
public class JacksonScalableConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern ;

    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            //出参时间数据序列化
            builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());

            //出参Long类型转换成String输出
            builder.serializerByType(Long.class, ToStringSerializer.instance);
        };
    }

}*/

package com.github.sylphlike.framework.basis;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局Jackson格式化配置，一旦定义后全局不可修改,参数使用个性化@JsonFormat 配置无效
 *
 *   LocalDateTime 序列化配置全局配置 全局格式为 yyyy-MM-dd HH:mm:ss
 *   LocalDate     序列化配置全局配置 全局格式为 yyyy-MM-dd
 *   LocalTime     序列化配置全局配置 全局格式为 HH:mm:ss
 *   Long类型转换成 String类型
 * <p>  time 17:56 2021/01/29  星期五 </p>
 * <p> email 15923508369@163.com     </P>
 * @author Gopal.pan
 * @version 1.0.0
 */
public class JsonConfig {

    // 默认时间类型序列化，反序列化格式
    private static final String DATE_TIME_PATTEN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTEN = "yyyy-MM-dd";
    private static final String TIME_PATTEN = "HH:mm:ss";



    public static ObjectMapper mapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModules(javaTimeModule,simpleModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }



    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTEN)));
        }
    }

    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext deserializationContext)throws IOException {
            return LocalDateTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(DATE_TIME_PATTEN));
        }
    }


    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ofPattern(DATE_PATTEN)));
        }
    }


    public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext deserializationContext)throws IOException {
            return LocalDate.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(DATE_PATTEN));
        }
    }



    public static class LocalTimeSerializer extends JsonSerializer<LocalTime>{
        @Override
        public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ofPattern(TIME_PATTEN)));
        }

    }


    public static class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonParser p, DeserializationContext deserializationContext)throws IOException {
            return LocalTime.parse(p.getValueAsString(), DateTimeFormatter.ofPattern(TIME_PATTEN));
        }
    }
}

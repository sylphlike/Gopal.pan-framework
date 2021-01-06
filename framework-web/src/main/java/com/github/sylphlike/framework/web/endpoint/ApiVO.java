package com.github.sylphlike.framework.web.endpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>  time 06/11/2020 15:51  星期五 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiVO {

    /** 应用名称 */
    private String  appName;


    /** controller 集合 */
    private List<Controllers> controllers;




    @Data
    public static class  Controllers{

        /** 类名 */
        private String simpleName;

        /** controller 权限定名称 */
        private String name;

        /** 描述 */
        private String desc;


        /** 接口集合 */
        private List<ApiInfo> apiInfoList;
    }

    @Data
    public static class ApiInfo{
        /** 请求类型 */
        private String methodType;

        /** url地址 */
        private String urlPath;

        /** 方法描述 */
        private String name;
    }

}

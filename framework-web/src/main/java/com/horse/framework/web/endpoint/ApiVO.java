package com.horse.framework.web.endpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>  time 06/11/2020 15:51  星期五 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiVO {

    private String  appName;


    private List<ApiInfo> apiInfoList;



    @Data
    public static class ApiInfo{
        private String methodType;

        private String urlPath;

        private String name;
    }

}

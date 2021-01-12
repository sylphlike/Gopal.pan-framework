package com.github.sylphlike.framework.amoeba.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>  time 04/12/2020 16:24  星期五 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sylphlike.datasource")
public class DataSourceInfo {

    private boolean  master;

    private String   driveClassName;


    private final MasterDataSource masterDataSource = new MasterDataSource();

    private final MultipleDataSource multipleDataSource = new MultipleDataSource();

    @Data
    public  static class MasterDataSource{
        private String name;
        private String jdbcUrl;
        private String userName;
        private String password;
    }


    @Data
    public  static class MultipleDataSource{
        private List<String> name;
        private List<String> jdbcUrl;
        private List<String> userName;
        private List<String> password;
    }


}

package com.github.sylphlike.framework.web.utils;

import com.github.sylphlike.framework.utils.general.OSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class ApplicationUtil {


    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationUtil.class);

    // 应用程序名称 取值为 编译后的项目名称
    public static String projectName;


    // 应用程序版本号
    public static String projectVersion;



    static {
        try {
            ApplicationHome home = new ApplicationHome(ApplicationUtil.class);
            File source = home.getSource();
            String absolutePath = source.getAbsolutePath();

            if(absolutePath.endsWith(".jar")){
                JarFile jarFile = new JarFile(source);
                Enumeration<JarEntry> entries = jarFile.entries();

                while (entries.hasMoreElements()){
                    JarEntry jarEntry = entries.nextElement();
                    String innerPath = jarEntry.getName();
                    if (innerPath.endsWith("MANIFEST.MF")) {
                        InputStream inputStream = jarFile.getInputStream(jarEntry);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = null;
                        while (null != (line = bufferedReader.readLine())) {
                            if(line.startsWith("Implementation-Title")){
                                String[] name = line.split(":");
                                projectName = name[1].trim();
                                LOGGER.info("【framework-web】jar包方式启动,当前项目名称[{}]",projectName);
                            }if(line.startsWith("Implementation-Version")){
                                String[] version = line.split(":");
                                projectVersion = version[1].trim();
                                LOGGER.info("【framework-web】jar包方式启动,当前项目版本[{}]",projectVersion);
                            }

                        }
                        bufferedReader.close();
                    }
                }
                jarFile.close();
            }else {
                // windows  D:\ideaWorkspace\Gopal.pan-private\Gopal.pan-schedule\Gopal.pan-schedule-client\target\classes
                // MAC      Users/horse/IdeaProjects/Gopal.pan-private/micro-api-auth/framework-utils-feign/target/classes

                String osName = OSUtils.systemConfig().get("osName");
                if(osName.startsWith("Windows")){
                    String fullProjectPath = absolutePath.substring(0, absolutePath.lastIndexOf("\\target\\classes"));
                    int projectIndex = fullProjectPath.lastIndexOf("\\");
                    projectName =  fullProjectPath.substring(projectIndex + 1, fullProjectPath.length());
                    projectVersion = "1.0.0-SNAPSHOT";
                }else {
                    String fullProjectPath = absolutePath.substring(0, absolutePath.lastIndexOf("/target/classes"));
                    int projectIndex = fullProjectPath.lastIndexOf("/");
                    projectName =  fullProjectPath.substring(projectIndex + 1, fullProjectPath.length());
                    projectVersion = "1.0.0-SNAPSHOT";

                }
            }

        } catch (IOException e) {
            LOGGER.error("【framework-web】获取应用系统属性异常",e);
        }

    }


}

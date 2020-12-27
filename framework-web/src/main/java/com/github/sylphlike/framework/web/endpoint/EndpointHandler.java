package com.github.sylphlike.framework.web.endpoint;

import com.github.sylphlike.framework.norm.CharsetUtil;
import com.github.sylphlike.framework.norm.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * <p>  time 06/11/2020 15:40  星期五 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 *  系统类对外提供接口服务
 * @author Gopal.pan
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/endpoint")
public class EndpointHandler {

    private final Logger LOGGER =  LoggerFactory.getLogger(EndpointHandler.class);

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public EndpointHandler(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    private static final List<String> FILTER_ADDRESS = new ArrayList<>( Arrays.asList(
            "/endpoint/business/url","/swagger-resources/configuration/security","/swagger-resources/configuration/ui",
            "/swagger-resources","/v2/api-docs-ext","/swagger-resources-ext","/error"
        ));


    @Value("${spring.application.name}")
    private String appName;

    /**
     * <p>  time 15:43 2020/11/6 【HH:mm yyyy/MM/dd】  </p>
     * <p> email 15923508369@163.com </p>
     * 系统中业务接口地址列表
     *
     * @return   com.github.sylphlike.norm.Response<java.lang.Object>
     * @author   Gopal.pan
     */
    @GetMapping(value = "/business/url",name = "业务接口地址列表")
    public Response<ApiVO> businessUrl(){
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        LOGGER.info("【framework-web】系统中全部URL地址个数为[{}]",map.size());

        List<ApiVO.ApiInfo> apiVOList = new ArrayList<>();
        ApiVO.ApiInfo apiInfo;

        Set<RequestMappingInfo> requestMappingInfos = map.keySet();
        for (RequestMappingInfo requestMappingInfo : requestMappingInfos) {
            Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
            String urlPath = "";
            if(!patterns.isEmpty()){
                urlPath = patterns.iterator().next();
            }

            String methodType = "";
            Set<RequestMethod> methods = requestMappingInfo.getMethodsCondition().getMethods();
            if(!methods.isEmpty()){
                methodType = methods.iterator().next().name();
            }

            if(!FILTER_ADDRESS.contains(urlPath) && StringUtils.isNotEmpty(urlPath)){
                apiInfo = new ApiVO.ApiInfo();
                apiInfo.setMethodType(methodType);
                apiInfo.setUrlPath(urlPath);
                apiInfo.setName(ObjectUtils.defaultIfNull(requestMappingInfo.getName(), CharsetUtil.CHAR_ENGLISH_EMPTY));
                apiVOList.add(apiInfo);
            }
        }

        ApiVO apiVO = ApiVO.builder().appName(appName).apiInfoList(apiVOList).build();
        LOGGER.info("【framework-web】系统业务URL地址个数为[{}]",apiVOList.size());
        return new Response<>(apiVO);

    }
}

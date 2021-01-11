package com.github.sylphlike.framework.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sylphlike.framework.basis.UserHelper;
import com.github.sylphlike.framework.web.callback.AbstractException;
import com.github.sylphlike.framework.web.exception.ServiceException;
import com.github.sylphlike.framework.basis.Constants;
import com.github.sylphlike.framework.norm.CharsetUtil;
import com.github.sylphlike.framework.norm.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

public class BaseController {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired protected ObjectMapper mapper;

    protected HttpServletRequest httpServletRequest;
    protected HttpServletResponse httpServletResponse;
    protected HttpSession httpSession;

    /** 登录用户ID，或商户ID */
    protected Long userId;



    /**
     * 初始化请求对象，
     * <p> 同步方法，保证多线程情况下线程安全
     * <p>  time 16:39 2021/1/11 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com
     * @param request   request
     * @param response  response
     * @author  Gopal.pan
     */
    @ModelAttribute
    public synchronized void setGeneralReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.httpServletRequest = request;
        this.httpServletResponse = response;
        this.httpSession = request.getSession();
        String identityId = request.getHeader(Constants.IDENTITY_ID);
        userId = StringUtils.isEmpty(identityId)? null : Long.valueOf(identityId);
        UserHelper.IDENTITY_ID.set(userId);

    }



    /**
     * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     * @param originName     原文件名
     * @return 重新编码后的文件名
     */
    protected String toUtf8String(HttpServletRequest request, String originName) {
        String agent = request.getHeader("User-Agent");
        try {
            boolean isFireFox = (agent != null && agent.toLowerCase().contains("firefox"));
            if (isFireFox) {
                originName = new String(originName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            } else {
                originName = CharsetUtil.toUtf8String(originName);
                if ((agent != null && agent.contains("MSIE"))) {
                    // see http://support.microsoft.com/default.aspx?kbid=816868
                    if (originName.length() > 150) {
                        // 根据request的locale 得出可能的编码
                        originName = new String(originName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return originName;
    }








    /**
     * <p>  time 16:10 2020/9/21 (HH:mm yyyy/MM/dd)
     * <p> email 15923508369@163.com 
     *
     *  包装controller层异常处理,业务controller层无需捕获处理异常
     *
     * @param LogDesc  业务功能描述
     * @return com.github.sylphlike.norm.Response
     * @author Gopal.pan
     */
    protected <T> Response<T> withException(String LogDesc,final AbstractException<T> exception){
        try {
            return exception.execute();
        } catch (ServiceException e) {
            LOGGER.error("【framework-web】【{}】{},业务异常,[{}]", logHeadline(),LogDesc,StringUtils.join("错误码:",e.getCode(),"错误描述:", e.getMessage()));
            return Response.error(e);
        } catch (Exception e){
            LOGGER.error("【framework-web】【{}】{},系统异常", logHeadline(),LogDesc,e);
            return Response.error(FReply.FW_UNKNOWN_ERROR);
        }
    }

    private String logHeadline() {
        String headline = "";
        RestController restController = this.getClass().getAnnotation(RestController.class);
        if(null != restController){
            String resValue = restController.value();
            if(StringUtils.isNotEmpty(resValue)){
                headline =  resValue;
            }else {
                headline = this.getClass().getSimpleName();
            }
        }
        return headline;
    }



}

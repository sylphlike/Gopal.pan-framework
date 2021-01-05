package com.github.sylphlike.framework.web.filter;


import com.github.sylphlike.framework.basis.Constants;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 单体应用跨域配置,如果是网关请求来源，
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 *
 * @author Gopal.pan
 * @version 1.0.0
 */


@WebFilter(urlPatterns="/*")
@Order(Constants.DIGITAL_TWO)
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader("origin"));
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST,GET,OPTIONS,DELETE,PUT");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization,token");
        chain.doFilter(req, res);
    }
}

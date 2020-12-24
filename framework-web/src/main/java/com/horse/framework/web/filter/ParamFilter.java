package com.horse.framework.web.filter;

import com.horse.framework.basis.Constants;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <p>  time 10/09/2020 18:19  星期四 【dd/MM/YYYY HH:mm】 </p>
 * <p> email 15923508369@163.com </p>
 *
 *  参数去除前后空格，XSS攻击过滤
 *
 * @author Gopal.pan
 * @version 1.0.0
 */

@WebFilter(urlPatterns="/*")
@Order(Constants.DIGITAL_ONE)
public class ParamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        ParamRequestWrapper wrapper = new ParamRequestWrapper(request);
        filterChain.doFilter(wrapper, response);
    }
}

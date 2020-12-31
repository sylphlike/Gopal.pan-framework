package com.github.sylphlike.framework.web.log;


import com.github.sylphlike.framework.basis.Constants;
import com.github.sylphlike.framework.basis.UUIDCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * <p>  time 10/09/2020 18:19  星期四 (dd/MM/YYYY HH:mm) 
 * <p> email 15923508369@163.com 
 *   在logback日志输出中增加MDC参数选项
 *          注意，此Filter尽可能的放在其他Filter之前
 *          默认情况下，将会把“requestId”、“requestSeq”、“localIp”、“timestamp”、“uri”添加到MDC上下文中。
 *          1）其中requestId，requestSeq为调用链跟踪使用，开发者不需要手动修改他们。
 *          2）localIp为当前web容器的宿主机器的本地IP，为内网IP。
 *          3）timestamp为请求开始被servlet处理的时间戳，设计上为被此Filter执行的开始时间，可以使用此值来判断内部程序执行的效率。
 *          4）uri为当前request的uri参数值。
 *          我们可以在logback.xml文件的layout部分，通过%X{key}的方式使用MDC中的变量
 *
 *       @WebFilter 执行顺序
 *       通过实践发现如果想要控制filer的执行顺序可以 通过控制filter的文件名来控制
 *          比如：
 *              UserLoginFilter.java 和 ApiLog.java 这两个文件里面分别是“用户登录检查过滤器”和“接口日志过滤器”，因为这两个文件的 首字母A排U之前 ，
 *              导致每次执行的时候都是先执行“接口日志过滤器”再执行“用户登录检查过滤器”，所以我们现在修改两个文件的名称分别为
 *              Filter0_UserLogin.java
 *              Filter1_ApiLog.java
 *              这样就能先执行“用户登录检查过滤器”再执行“接口日志过滤器”
 * @author Gopal.pan
 * @version 1.0.0
 */


@WebFilter(urlPatterns="/*")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig)  {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest hsr = (HttpServletRequest)servletRequest;
            String trace = hsr.getHeader(Constants.TRACE);
            if(StringUtils.isEmpty(trace)){
                trace =UUIDCache.UUID();
            }

            MDC.put(Constants.TRACE,trace);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }

    }


    @Override
    public void destroy() {
    }
}

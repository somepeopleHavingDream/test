package org.yangxin.test.spring.filterregistrationbean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yangxin
 * 2021/12/6 11:42
 */
public class Test1Filter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("自定义过滤器filter1触发，拦截url：" + request.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
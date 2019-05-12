package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "test",value = "/jpalogin")
public class loginfilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("----------------------->过滤器被创建");
    }

    @Override
    public void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) var1;
        String uri=request.getRequestURI();

        var3.doFilter(var1, var2);
    }
    @Override
    public void destroy() {

        System.out.println("----------------------->过滤器被销毁");
    }
}

package com.helene.venteplats.filterConfig;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class SecondFiltre implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Remote Host:"+request.getRemoteHost());
        System.out.println("Remote Address:"+request.getRemoteAddr());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}

package com.helene.venteplats.filterConfig;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PremierFiltre implements Filter {

    //this method will be called by container when we send any request
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("doFilter() method is invoked");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        System.out.println("Context path is  " + httpServletRequest.getContextPath());
        chain.doFilter(httpServletRequest, httpServletResponse);
        System.out.println("doFilter() method is ended");

    }

    // this method will be called by container while deployment
    public void init(FilterConfig config) throws ServletException {

        System.out.println("init() method has been get invoked");
        System.out.println("Filter name is "+config.getFilterName());
        System.out.println("ServletContext name is"+config.getServletContext());
        System.out.println("init() method is ended");
    }

    public void destroy() {
        //do some stuff like clearing the resources

    }
}

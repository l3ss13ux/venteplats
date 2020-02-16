package com.helene.venteplats.filterConfig;

import com.helene.venteplats.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFiltre implements Filter {
    @Autowired
    UtilisateurService utilisateurService;

    //this method will be called by container when we send any request
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("doFilter() method is invoked");
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        int idUser = Integer.parseInt(httpServletRequest.getHeader("idCurrentUser"));

        if(utilisateurService.recupererUtilisateur(idUser) == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Vous n'existez pas en base de " +
                    "données donc vous ne pouvez rien faire");
            return;
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
        System.out.println("doFilter() method is ended");
    }

    // this method will be called by container while deployment
    public void init(FilterConfig config) throws ServletException {

        System.out.println("init() method has been get invoked");
        //System.out.println("Filter name is "+config.getFilterName());
        //System.out.println("ServletContext name is"+config.getServletContext());
        System.out.println("init() method is ended");
    }

    public void destroy() {
        //do some stuff like clearing the resources

    }
}

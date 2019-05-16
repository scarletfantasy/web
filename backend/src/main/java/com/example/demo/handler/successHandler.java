package com.example.demo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;


@Component("successhandler")
public class successHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        User user = (User) auth.getPrincipal();
        String id=user.getUsername();
        Collection<GrantedAuthority> authorities= user.getAuthorities();

        if(authorities.contains(new SimpleGrantedAuthority("BAN")))
        {

            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().write("2");

        }
        else
        {
            HttpSession session=request.getSession();

            session.setAttribute("id",id);
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.getWriter().write("1");
        }



    }
}

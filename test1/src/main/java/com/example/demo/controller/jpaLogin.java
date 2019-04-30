package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import  java.util.*;

@RestController
public class jpaLogin {
    @Autowired
    userRepo userrepo;

    @RequestMapping(value="/jpalogin")
    public Object login(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        String lid=request.getParameter("id");
        String lpassword=request.getParameter("password");
        List<User> alluser=userrepo.getAllUser();
        response.setHeader("Access-Control-Allow-Origin", "*");
        session.setAttribute("id"," ");
        for(User user:alluser)
        {
            if(user.getId().equals(lid)&&user.getPassword().equals(lpassword))
            {
                System.out.println("success");
                session.setAttribute("id",lid);
                return 1;
            }
        }



        return 0;
    }

    @RequestMapping(value="/jparegister")
    public Object register(HttpServletResponse response, HttpServletRequest request)
    {
        String id=request.getParameter("id");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        System.out.println("success\n");
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<User> alluser=userrepo.getAllUser();
        for(User user:alluser)
        {
            if(user.getId().equals(id))
            {
                return "same id please change it";
            }
        }
        userrepo.insertUser(id,password,email);

        return "success";
    }

    @RequestMapping(value = "/jpalogout")
    public Object logout(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        response.setHeader("Access-Control-Allow-Origin", "*");
        session.removeAttribute("id");
        return "success";
    }
}

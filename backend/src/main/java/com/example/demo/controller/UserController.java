package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.userDao;
import com.example.demo.entity.User;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    userDao userdao;
    @Autowired
    userService userservice;

    @RequestMapping(value="/jpatest")
    public Object test(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        List<User> alluser=userdao.getalluser();
        for(User user:alluser)
        {
            if(user.getId().equals("user4"))
            {
                user.setPassword("abcd");
                userdao.edituser(user);
            }

        }


        return 0;
    }

    @RequestMapping(value="/jpalogin")
    public Object login(HttpServletResponse response, HttpServletRequest request)
    {
        String lid=request.getParameter("id");
        String lpassword=request.getParameter("password");
        HttpSession session=request.getSession();

        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        List<User> alluser=userdao.getalluser();
        System.out.println(lid);
        System.out.println(lpassword);
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
        System.out.println("success\n");/*
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        if(userdao.getuserbyid(id).isPresent())
        {
            return "fail";
        }

        User newuser=new User(id,password,email);

        userdao.edituser(newuser);*/

        return userservice.register(id,email,password);
    }

    @RequestMapping(value = "/jpalogout")
    public Object logout(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        session.removeAttribute("id");
        return "success";
    }
    @RequestMapping(value = "/jpashowuser")
    public Object showalluser(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        return userdao.getalluser();
    }
    @RequestMapping(value = "/jpauseredit")
    public Object useredit(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        String json=request.getParameter("user");
        JSONObject jsonobeject= JSON.parseObject(json);
        String id=jsonobeject.getString("id");
        String role=jsonobeject.getString("role");
        String password=jsonobeject.getString("password");
        String email=jsonobeject.getString("email");
        User user=new User(id,password,email,role);
        userdao.edituser(user);
        return 0;
    }
}
package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class OrderController {

    @Autowired
    orderService orderservice;

    @RequestMapping(value="/historytest")
    public Object test1()
    {
        /*Book book=bookdao.getbookbyid("949849444").get();
        User user=userdao.getuserbyid("admin").get();
        History history=new History("123","1","admin","123",book,user);
        historydao.edithistory(history);
        historydao.flush();*/

        return 0;



    }
    @RequestMapping(value="/jpaaddtocart")
    public Object addtocart(HttpServletResponse response, HttpServletRequest request)
    {
        HttpSession session=request.getSession();
        String id=(String)session.getAttribute("id");


        String json=request.getParameter("book");
        JSONObject jsonobeject= JSON.parseObject(json);

        String isbn=jsonobeject.getString("isbn");


        String number=request.getParameter("number");
        int number1=Integer.parseInt(number);
        String time=request.getParameter("time");


        return orderservice.addtocart(id,isbn,number1,time);
    }

    @RequestMapping(value="/jpacleancart")
    public Object cleancart(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {


        String id=(String)session.getAttribute("id");




        return orderservice.cleancart(id);
    }

    @RequestMapping(value="/jpashowcart")
    public Object showcart(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        String id=(String)session.getAttribute("id");


        return orderservice.showcart(id);
    }

    @RequestMapping(value="/jpashowhistory")
    public Object showhistory(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        String id=(String)session.getAttribute("id");


        return orderservice.showhistory(id);
    }

    @RequestMapping(value="/jpadeleteorder")
    public Object deleteorder(HttpServletResponse response, HttpServletRequest request)
        {
        String id=request.getParameter("id");




        return orderservice.deleteorder(id);
    }
}

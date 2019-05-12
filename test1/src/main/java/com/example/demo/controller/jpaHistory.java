package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Book;
import com.example.demo.entity.History;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.repo.bookRepo;
import com.example.demo.repo.historyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dao.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@RestController
public class jpaHistory {
    @Autowired
    historyRepo historyrepo;
    @Autowired
    bookRepo bookrepo;
    @Autowired
    bookDao bookdao;
    @Autowired
    historyDao historydao;
    @Autowired
    userDao userdao;
    @Autowired
    orderDao orderdao;

    @RequestMapping(value="/historytest")
    public Object test1()
    {
        /*Book book=bookdao.getbookbyid("949849444").get();
        User user=userdao.getuserbyid("admin").get();
        History history=new History("123","1","admin","123",book,user);
        historydao.edithistory(history);
        historydao.flush();*/
        List<History> allhistory=historydao.getallhistory();
        for(History history:allhistory)
        {
            return history;
        }
        return 0;



    }
    @RequestMapping(value="/jpaaddtocart")
    public Object addtocart(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {

        String id=(String)session.getAttribute("id");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(id==null)
        {
            return "login";
        }
        String json=request.getParameter("book");
        JSONObject jsonobeject= JSON.parseObject(json);

        String isbn=jsonobeject.getString("isbn");
        Book book=bookdao.getbookbyid(isbn).get();
        User user=userdao.getuserbyid(id).get();

        String number=request.getParameter("number");
        int number1=Integer.parseInt(number);
        String date=request.getParameter("time");
        Order order=new Order(date,number1,book,user);
        orderdao.editorder(order);

        return "success";
    }

    @RequestMapping(value="/jpacleancart")
    public Object cleancart(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {

        String result="success";
        String id=(String)session.getAttribute("id");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(id==null)
        {
            return "login";
        }
        List<Order> cart=orderdao.getorderbyuserid(id);

        for(Order order:cart)
        {
            if(order.getbook().getnumber()>=order.getnumber())
            {
                History history=order.o2h();
                Book book=order.getbook();
                book.setnumber(book.getnumber()-order.getnumber());
                bookdao.editbook(book);
                historydao.edithistory(history);
                orderdao.deleteorder(order);
            }

        }
        orderdao.flush();


        return orderdao.getorderbyuserid(id);
    }

    @RequestMapping(value="/jpashowcart")
    public Object showcart(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        String id=(String)session.getAttribute("id");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(id==null)
        {
            return "login";
        }
        List<Order> cart=orderdao.getorderbyuserid(id);
        System.out.println("success");
        return cart;
    }

    @RequestMapping(value="/jpashowhistory")
    public Object showhistory(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {
        String id=(String)session.getAttribute("id");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(id==null)
        {
            return "login";
        }
        List<History> cart=historydao.gethistorybyid(id);
        System.out.println("success");
        return cart;
    }

    @RequestMapping(value="/jpadeleteorder")
    public Object deleteorder(HttpServletResponse response, HttpServletRequest request)
        {
        String id=request.getParameter("id");
            response.setHeader("Access-Control-Allow-Origin", "*");
            if(id==null)
            {
                return "login";
            }
        Long lid=Long.parseLong(id);
            orderdao.deleteorder(orderdao.getbyid(lid));


        return "success";
    }
}

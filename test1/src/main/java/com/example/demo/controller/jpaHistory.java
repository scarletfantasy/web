package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Book;
import com.example.demo.entity.History;
import com.example.demo.repo.bookRepo;
import com.example.demo.repo.historyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String bookname=jsonobeject.getString("bookname");
        String isbn=jsonobeject.getString("isbn");
        String bookimg=jsonobeject.getString("bookimg");
        String price=jsonobeject.getString("price");

        String number=request.getParameter("number");
        String date=request.getParameter("time");
        historyrepo.insertHistory(Integer.parseInt(number),Double.parseDouble(price),date,id,isbn);

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
        List<History> cart=historyrepo.getshoppingcar(id,"0");
        LinkedList<History> changedCart=new LinkedList<>() ;
        for(History history:cart)
        {
            Book tmp=bookrepo.getBook(history.getisbn());
            if(tmp==null)
            {
                changedCart.add(history);
            }
            else if(tmp.getnumber()>=history.getnumber())
            {
                System.out.println(tmp.getnumber());
                System.out.println(history.getnumber());
                bookrepo.updateBook(tmp.getisbn(),tmp.getbookimg(),tmp.getbookname(),tmp.getnumber()-history.getnumber(),tmp.getprice());
                historyrepo.updateHistory(history.getid());

            }
            else
            {

                changedCart.add(history);
            }

        }

        System.out.println(changedCart.size());
        return changedCart;
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
        String isfinish=request.getParameter("isfinish");

        List<History> allhistory=historyrepo.getshoppingcar(id,isfinish);
        System.out.println("success");
        System.out.println(allhistory.size());

        return allhistory;
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
        historyrepo.deleteHistory(id);

        return "success";
    }
}

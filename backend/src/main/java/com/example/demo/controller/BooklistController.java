package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class BooklistController {

    @Autowired
    bookService bookservice;
    @RequestMapping(value="/jpabooklist")
    public Object booklist(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {




        return bookservice.booklist();
    }
    @RequestMapping(value="/jpaeditsave")
    public Object editsave(HttpServletRequest request,HttpServletResponse response, HttpSession session)
    {

        String json=request.getParameter("book");
        JSONObject jsonobeject= JSON.parseObject(json);
        String bookname=jsonobeject.getString("bookname");
        String isbn=jsonobeject.getString("isbn");
        String bookimg=jsonobeject.getString("bookimg");
        double price=jsonobeject.getDouble("price");
        int number=jsonobeject.getInteger("number");



        return bookservice.editsave(bookname,isbn,bookimg,price,number);
    }
    @RequestMapping(value = "/jpaeditdelete")
    public Object editdelete(HttpServletRequest request,HttpServletResponse response, HttpSession session)
    {
        String isbn=request.getParameter("isbn");

        return bookservice.editdelete(isbn);
    }



}

package com.example.demo.controller;
import com.example.demo.dao.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Book;
import com.example.demo.repo.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class jpaBooklist {
    @Autowired
    bookRepo bookrepo;
    @Autowired
    bookDao bookdao;
    @RequestMapping(value="/jpabooklist")
    public Object booklist(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {


        List<Book> books=bookdao.getallbook();
        response.setHeader("Access-Control-Allow-Origin", "*");
        return books;
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
        Book book=new Book();
        book.setisbn(isbn);
        book.setbookimg(bookimg);
        book.setbookname(bookname);
        book.setnumber(number);
        book.setprice(price);
        bookdao.editbook(book);

        response.setHeader("Access-Control-Allow-Origin", "*");
        return 0;
    }
    @RequestMapping(value = "/jpaeditdelete")
    public Object editdelete(HttpServletRequest request,HttpServletResponse response, HttpSession session)
    {
        String isbn=request.getParameter("isbn");
        System.out.println(isbn);
        bookdao.deletbookbyisbn(isbn);
        return 0;
    }



}

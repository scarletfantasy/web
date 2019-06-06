package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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
    @RequestMapping(value="/uploadimg")
    public Object uploadimg (HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        MultipartHttpServletRequest mprequest = (MultipartHttpServletRequest) request;
        MultipartFile file=mprequest.getFile("img");
        String index=mprequest.getParameter("isbn");
        String houzhui=mprequest.getParameter("houzhui");
        String url="D:\\apache-tomcat-8.5.41\\webapps\\img\\"+index+"."+houzhui;

        System.out.println( file.getName());
        File imgfile=new java.io.File(url);
        file.transferTo(imgfile);

        

        return bookservice.uploadimg(index+"."+houzhui,index);
    }

    @RequestMapping(value="/getdetail")
    public Object getdetail(@RequestParam(value="isbn") String isbn)
    {
        return bookservice.findbook(isbn);
    }



}

package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        String isbn=mprequest.getParameter("isbn");


        

        return bookservice.uploadimg(file.getBytes(),isbn);
    }

    @RequestMapping(value="/getdetail")
    public Object getdetail(@RequestParam(value="isbn") String isbn)
    {
        return bookservice.findbook(isbn);
    }
    @RequestMapping(value="/jpaintroduction")
    public Object editintroduction(@RequestParam(value="isbn") String isbn,@RequestParam(value="introduction") String intro)
    {
        return bookservice.savecomments(isbn,intro);
    }
    @GetMapping(value="/findimg/{isbn}" , produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Object findimg(@PathVariable String isbn)
    {

        return bookservice.findimg(isbn);

    }


}

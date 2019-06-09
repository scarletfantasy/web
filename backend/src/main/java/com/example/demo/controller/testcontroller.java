package com.example.demo.controller;

import com.example.demo.dao.bookDao;
import com.example.demo.dao.historyDao;
import com.example.demo.dao.userDao;
import com.example.demo.entity.bookimage;
import com.example.demo.repo.commentsRepo;
import com.example.demo.repo.imageRepo;
import com.example.demo.repo.orderRepo;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class testcontroller {

    @Autowired
    orderRepo orderrepo;
    @Autowired
    bookDao bookdao;
    @Autowired
    historyDao historydao;
    @Autowired
    userDao userdao;
    @Autowired
    commentsRepo commentsrepo;

    @Autowired
    imageRepo imagerepo;
        @RequestMapping(value="/testimg")
        public Object uploadimg (HttpServletRequest request, HttpServletResponse response) throws IOException,NullPointerException
        {
            MultipartHttpServletRequest mprequest = (MultipartHttpServletRequest) request;
            MultipartFile file=mprequest.getFile("img");

            String isbn=mprequest.getParameter("isbn");
            bookimage image=new bookimage();
            image.setIsbn(isbn);
            image.setContent(new Binary(file.getBytes()));
            imagerepo.save(image);


            return 0;




        }



}

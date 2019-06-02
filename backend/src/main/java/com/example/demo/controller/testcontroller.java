package com.example.demo.controller;

import com.example.demo.dao.bookDao;
import com.example.demo.dao.historyDao;
import com.example.demo.dao.userDao;
import com.example.demo.entity.bookimage;
import com.example.demo.repo.imageRepo;
import com.example.demo.repo.orderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    imageRepo imagerepo;
    @RequestMapping(value="/test")
    public Object test()
    {
        System.out.println("success");
        bookimage image1=new bookimage();
        image1.setisbn("123");
        image1.setimg("456");
        imagerepo.save(image1);

        return imagerepo.findAll();

    }
}

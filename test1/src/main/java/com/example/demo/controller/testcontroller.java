package com.example.demo.controller;

import com.example.demo.dao.bookDao;
import com.example.demo.dao.historyDao;
import com.example.demo.dao.userDao;
import com.example.demo.repo.*;
import com.example.demo.entity.*;
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
    @RequestMapping(value="/testorder")
    public Object test()
    {
        User user=userdao.getuserbyid("admin").get();
        Book book=bookdao.getbookbyid("949849444").get();

        Order order=new Order("123",10,book,user);
        orderrepo.save(order);
        orderrepo.flush();
        return orderrepo.getorderbyid("admin");

    }
}

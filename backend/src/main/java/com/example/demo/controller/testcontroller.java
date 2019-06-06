package com.example.demo.controller;

import com.example.demo.dao.bookDao;
import com.example.demo.dao.historyDao;
import com.example.demo.dao.userDao;
import com.example.demo.entity.bookcomments;
import com.example.demo.repo.commentsRepo;
import com.example.demo.repo.orderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

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

    @RequestMapping(value="/test")
    public Object test()
    {
        System.out.println("success");
        bookcomments comment1=new bookcomments();
        comment1.setisbn("974564564");
        List<String> comments=new LinkedList<>();
        comment1.setcomment(comments);
        comment1.addcomment("taiqiangle");
        comment1.addcomment("dqwdqdwqd");
        comment1.setintroduction("harry potter");
        commentsrepo.save(comment1);

        return bookdao.getcommentbyisbn("949849444");

    }
}

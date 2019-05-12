package com.example.demo.service;

import com.example.demo.dao.userDao;
import com.example.demo.entity.User;
import com.example.demo.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class userService {


    @Autowired
    userDao userdao;




    public Object login(String lid,String lpassword)
    {

        List<User> alluser=userdao.getalluser();

        for(User user:alluser)
        {
            if(user.getId().equals(lid)&&user.getPassword().equals(lpassword))
            {
                System.out.println("success");

                return 1;
            }
        }



        return 0;
    }


    public Object register(String id,String email,String password)
    {

        System.out.println("success\n");

        if(userdao.getuserbyid(id).isPresent())
        {
            return "fail";
        }

        User newuser=new User(id,password,email);

        userdao.edituser(newuser);

        return "success";
    }


    public Object logout(HttpServletResponse response, HttpServletRequest request, HttpSession session)
    {

        return "success";
    }

}

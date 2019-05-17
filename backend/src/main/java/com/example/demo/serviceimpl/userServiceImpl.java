package com.example.demo.serviceimpl;

import com.example.demo.dao.userDao;
import com.example.demo.entity.User;
import com.example.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userServiceImpl implements userService {


    @Autowired
    userDao userdao;



    @Override
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

    @Override
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

    @Override
    public Object logout()
    {

        return "success";
    }



}

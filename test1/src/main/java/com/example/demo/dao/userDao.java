package com.example.demo.dao;
import com.example.demo.entity.User;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class userDao  {
    @Autowired
    userRepo userrepo;



    public List<User> getalluser()
    {
        return userrepo.findAll();
    }
    public void edituser(User user)
    {
        userrepo.save(user);
    }
    public void flush()
    {
        userrepo.flush();
    }
    public Optional<User> getuserbyid(String id)
    {
       return userrepo.findById(id);
    }

}

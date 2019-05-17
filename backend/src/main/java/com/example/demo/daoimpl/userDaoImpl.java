package com.example.demo.daoimpl;

import com.example.demo.dao.userDao;
import com.example.demo.entity.User;
import com.example.demo.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class userDaoImpl implements userDao {
    @Autowired
    userRepo userrepo;


    @Override
    public List<User> getalluser()
    {
        return userrepo.findAll();
    }
    @Override
    public void edituser(User user)
    {
        userrepo.save(user);
    }
    @Override
    public void flush()
    {
        userrepo.flush();
    }
    @Override
    public Optional<User> getuserbyid(String id)
    {
       return userrepo.findById(id);
    }

}

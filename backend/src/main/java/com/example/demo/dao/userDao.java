package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.entity.Userimage;

import java.util.List;
import java.util.Optional;

public interface userDao {
    public List<User> getalluser();
    public void edituser(User user);
    public void flush();
    public Optional<User> getuserbyid(String id);
    public List<Userimage> getimagebyid(String id);
    public void edituserimg(Userimage image);
}

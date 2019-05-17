package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface userDao {
    public List<User> getalluser();
    public void edituser(User user);
    public void flush();
    public Optional<User> getuserbyid(String id);
}

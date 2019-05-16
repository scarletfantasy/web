package com.example.demo.service;

public interface userService {
    public Object login(String lid,String lpassword);



    public Object register(String id,String email,String password);



    public Object logout();


}

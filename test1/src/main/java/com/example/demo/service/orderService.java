package com.example.demo.service;

public interface orderService {


    public Object addtocart(String id,String isbn,int number,String time);



    public Object cleancart(String id);



    public Object showcart(String id);



    public Object showhistory(String id);



    public Object deleteorder(String id);

}

package com.example.demo.service;

public interface bookService {
    public Object booklist();
    public Object editsave(String bookname,String isbn,String bookimg,double price,int number);
    public Object editdelete(String isbn);
}

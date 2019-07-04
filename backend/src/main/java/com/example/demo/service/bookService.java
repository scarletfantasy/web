package com.example.demo.service;

public interface bookService {
    public Object booklist();
    public Object editsave(String bookname,String isbn,String bookimg,double price,int number);
    public Object editdelete(String isbn);
    public Object uploadimg(byte[] data,String isbn);
    public Object findbook(String isbn);
    public Object savecomments(String isbn,String intro);
    public Object findimg(String isbn);
    public Object addcomment(String comment,String isbn);
}

package com.example.demo.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="book")
public class Book {
@Id
    private String isbn;
    private  String bookname;
    private String bookimg;
    private  double price;
    private int number;
    public  String getisbn()
    {
        return isbn;
    }
    /*@OneToMany
    private List<History> history;*/
    public  String getbookname()
    {
        return bookname;
    }
    public  String getbookimg()
    {
        return bookimg;
    }
    public  double getprice()
    {
        return price;
    }
    public  int getnumber()
    {
        return number;
    }
    public  void setisbn(String isbn)
    {
        this.isbn=isbn;
        return;
    }
    public  void setbookname(String bookname)
    {
        this.bookname=bookname;
        return;
    }
    public  void setbookimg(String bookimg)
    {
        this.bookimg=bookimg;
        return;
    }
    public  void setprice(double price)
    {
        this.price=price;
        return;
    }
    public  void setnumber(int number)
    {
        this.number=number;
        return;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}

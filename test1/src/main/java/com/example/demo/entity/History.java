package com.example.demo.entity;


import  javax.persistence.*;

@Entity
@Table(name="history")
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String time,userid,isbn,isfinish;
    private double price;
    private int number;
    public Long getid()
    {
        return id;
    }
    public int getnumber()
    {
        return number;
    }
    public double getprice()
    {
        return price;
    }
    public String gettime()
    {
        return time;
    }
    public String getuserid()
    {
        return userid;
    }
    public String getisbn()
    {
        return isbn;
    }
    public String getisfinish()
    {
        return isfinish;
    }
}

package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import  javax.persistence.*;

@Entity
@Table(name="history")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class History {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String time,userid,isbn;
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


    public History()
    {

    }
    public History(String time,String userid,String isbn,int number,double price)
    {

        this.time=time;
        this.isbn=isbn;
        this.price=price;
        this.number=number;
        this.userid=userid;
    }

}

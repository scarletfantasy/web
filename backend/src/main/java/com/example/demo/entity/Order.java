package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import  javax.persistence.*;

@Entity
@Table(name="userorder")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String time;
    private int number;
    @OneToOne
    private Book book;
    @OneToOne
    private User user;
    public Long getid()
    {
        return id;
    }
    public int getnumber()
    {
        return number;
    }
    public String gettime()
    {
        return time;
    }
    public Book getbook(){return book;}
    public User getuser(){return user;}
    public Order()
    {

    }
    public Order(String time,int number,Book book,User user)
    {
        this.time=time;
        this.number=number;
        this.book=book;
        this.user=user;
    }
    public History o2h()
    {
        History history =new History(this.time,this.user.getId(),this.book.getisbn(),this.number,this.book.getprice());
        return history;
    }


}

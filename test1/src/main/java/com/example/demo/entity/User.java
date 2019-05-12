package com.example.demo.entity;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name="user")
public class User {
    @Id
    private String id;
    private String password;
    private String email;
    /*@OneToMany
    private List<History> history;*/
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String pass)
    {
        this.password=pass;
    }
    public String getId()
    {
        return this.id;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public User(String tid,String tpassword,String temail)
    {
        id=tid;
        password=tpassword;
        email=temail;
    }
    public User()
    {

    }
}

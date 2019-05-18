package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class User {
    @Id
    private String id;
    private String password;
    private String email;
    private String role;
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
    public void setRole(String role){this.role=role;}
    public String getRole(){return this.role;}
    public User(String tid,String tpassword,String temail)
    {
        id=tid;
        password=tpassword;
        email=temail;
        role="USER";

    }
    public User(String tid,String tpassword,String temail,String trole)
    {
        id=tid;
        password=tpassword;
        email=temail;
        role=trole;
    }


    public User()
    {

    }
}

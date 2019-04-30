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
    public String getId()
    {
        return this.id;
    }
}

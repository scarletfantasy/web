package com.example.demo.entity;

import javax.persistence.Id;

public class bookimage {
    @Id
    private String isbn;
    private String img;
    public void setisbn(String isbn)
    {
        this.isbn=isbn;
    }
    public void setimg(String img)
    {
        this.img=img;
    }
    public String getisbn()
    {
        return this.isbn;
    }
    public String getimg(String img)
    {
        return this.img;
    }

}

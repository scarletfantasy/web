package com.example.demo.entity;

import javax.persistence.Id;
import java.util.List;

public class bookcomments {
    @Id
    private String isbn;
    private List<String> comments;
    public void setisbn(String isbn)
    {
        this.isbn=isbn;
    }
    public String getisbn()
    {
        return this.isbn;
    }

    public void setcomment(List<String> comments){this.comments=comments;}
    public void addcomment(String comment){this.comments.add(comment);}

    public List<String> getComments() {
        return comments;
    }
}

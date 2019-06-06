package com.example.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
@Document(collection = "comments")
public class bookcomments {
    @Id
    private String isbn;
    private List<String> comments;
    private String introduction;
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
    public String getintroduction(){return introduction;}
    public void setintroduction(String introduction){this.introduction=introduction;}
}

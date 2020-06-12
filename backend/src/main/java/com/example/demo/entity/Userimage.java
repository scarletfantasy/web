package com.example.demo.entity;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Lob;

@Document(collection = "userimage")
public class Userimage {
    @Id
    private String userid;

    @Lob
    private Binary content;

    public void setContent(Binary content) {
        this.content = content;
    }

    public Binary getContent() {
        return content;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }
}

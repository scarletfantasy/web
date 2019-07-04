package com.example.demo.entity;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookimage")
public class bookimage {

    @Id
    private String isbn;
    private String name; // 文件名

    private Binary content; // 文件内容
    private String contentType; // 文件类型
    private long size; // 文件大小

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setContent(Binary content) {
        this.content = content;
    }

    public Binary getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }
    // getter/setter

}

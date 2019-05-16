package com.example.demo.dao;

import com.example.demo.entity.Book;

import java.util.List;
import java.util.Optional;

public interface bookDao {
    public List<Book> getallbook();
    public void editbook(Book book);
    public void deletebook(Book book);
    public void deletbookbyisbn(String isbn);
    public void flush();
    public Optional<Book> getbookbyid(String id);

}

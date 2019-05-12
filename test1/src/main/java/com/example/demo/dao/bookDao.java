package com.example.demo.dao;
import com.example.demo.entity.*;
import com.example.demo.repo.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class bookDao {
    @Autowired
    bookRepo bookrepo;
    public List<Book> getallbook()
    {
        return bookrepo.findAll();
    }
    public void editbook(Book book)
    {
        bookrepo.save(book);
    }
    public void deletebook(Book book)
    {
        bookrepo.delete(book);
    }
    public void deletbookbyisbn(String isbn)
    {
        bookrepo.deleteById(isbn);
    }
    public void flush()
    {
        bookrepo.flush();
    }
    public Optional<Book> getbookbyid(String id)
    {
        return bookrepo.findById(id);

    }

}

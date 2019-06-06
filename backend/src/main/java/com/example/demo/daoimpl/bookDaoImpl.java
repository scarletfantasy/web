package com.example.demo.daoimpl;

import com.example.demo.dao.bookDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.bookcomments;
import com.example.demo.repo.bookRepo;
import com.example.demo.repo.commentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class bookDaoImpl implements bookDao {
    @Autowired
    bookRepo bookrepo;
    @Autowired
    commentsRepo commentsrepo;
    @Override
    public List<Book> getallbook()
    {
        return bookrepo.findAll();
    }
    @Override
    public void editbook(Book book)
    {
        bookrepo.save(book);
    }
    @Override
    public void deletebook(Book book)
    {
        bookrepo.delete(book);
    }
    @Override
    public void deletbookbyisbn(String isbn)
    {
        bookrepo.deleteById(isbn);
    }
    @Override
    public void flush()
    {
        bookrepo.flush();
    }
    @Override
    public Optional<Book> getbookbyid(String id)
    {
        return bookrepo.findById(id);

    }
    @Override
    public List<bookcomments> getcommentbyisbn(String isbn)
    {
        List<bookcomments> comments=commentsrepo.findByIsbn(isbn);
        return comments;
    }
    @Override
    public void editcomments(bookcomments comments)
    {
        commentsrepo.save(comments);
    }
}

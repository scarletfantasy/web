package com.example.demo.repo;


import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface bookRepo extends JpaRepository<Book, String>{
    @Query("SELECT b from Book b")
    List<Book> getAllBook();
    @Query("SELECT b from Book b where b.isbn=?1")
    Book getBook(String isbn);

    @Transactional
    @Modifying
    @Query(value="insert into book(isbn,bookimg,bookname,number,price) VALUES(?1,?2,?3,?4,?5)",nativeQuery = true)
    int insertBook(String isbn,String bookimg,String bookname,int number,double price);

    @Transactional
    @Modifying
    @Query(value="delete from book where isbn=?1",nativeQuery = true)
    int deleteBook(String isbn);
    @Transactional
    @Modifying
    @Query(value = "update book set bookname=?3 , bookimg=?2,number=?4,price=?5 where isbn=?1",nativeQuery = true)
    int updateBook(String isbn,String bookimg,String bookname,int number,double price);








}

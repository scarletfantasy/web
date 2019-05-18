package com.example.demo.serviceimpl;

import com.example.demo.dao.bookDao;
import com.example.demo.entity.Book;
import com.example.demo.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class bookServiceImpl implements bookService {

    @Autowired
    bookDao bookdao;
    @Override
    public Object booklist()
    {


        List<Book> books=bookdao.getallbook();

        return books;
    }
    @Override
    public Object editsave(String bookname,String isbn,String bookimg,double price,int number)
    {


        Book book=new Book();
        book.setisbn(isbn);
        book.setbookimg(bookimg);
        book.setbookname(bookname);
        book.setnumber(number);
        book.setprice(price);

        bookdao.editbook(book);


        return 0;
    }
    @Override
    public Object editdelete(String isbn) {

        bookdao.deletbookbyisbn(isbn);
        return 0;
    }
}

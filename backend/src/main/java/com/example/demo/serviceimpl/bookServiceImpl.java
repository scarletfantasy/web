package com.example.demo.serviceimpl;

import com.example.demo.dao.bookDao;
import com.example.demo.dao.orderDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.bookcomments;
import com.example.demo.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class bookServiceImpl implements bookService {

    @Autowired
    bookDao bookdao;
    @Autowired
    orderDao orderdao;
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

        orderdao.deletorderbybook(isbn);
        bookdao.deletbookbyisbn(isbn);
        return 0;
    }
    @Override
    public Object uploadimg(String bookimg,String isbn)
    {
        Book book=bookdao.getbookbyid(isbn).get();
        book.setbookimg("http://localhost:8081/img/"+bookimg);
        bookdao.editbook(book);
        bookdao.flush();
        return "upload success";
    }
    @Override
    public Object findbook(String isbn)
    {
        Book book=bookdao.getbookbyid(isbn).get();
        Map<String,Object> container=new HashMap<>();
        container.put("book",book);
        bookcomments comments;
        if(bookdao.getcommentbyisbn(isbn).size()>0)
        {
            comments=bookdao.getcommentbyisbn(isbn).get(0);
        }
        else
        {
            comments=null;
        }
        container.put("comments",comments);
        return container;

    }
}

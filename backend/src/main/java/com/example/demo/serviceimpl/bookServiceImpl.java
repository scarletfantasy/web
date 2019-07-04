package com.example.demo.serviceimpl;

import com.example.demo.dao.bookDao;
import com.example.demo.dao.orderDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.bookcomments;
import com.example.demo.service.bookService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
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
    public Object uploadimg(byte[] data,String isbn)
    {
        bookcomments comment;
        if(bookdao.getcommentbyisbn(isbn).size()!=0)
        {
            comment=bookdao.getcommentbyisbn(isbn).get(0);
        }
        else
        {
            comment=new bookcomments();
            comment.setisbn(isbn);
        }
        comment.setContent(new Binary(data));
        bookdao.editcomments(comment);
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
    @Override
    public Object savecomments(String isbn,String intro)
    {
        List<bookcomments> comments=bookdao.getcommentbyisbn(isbn);
        if(comments.size()==0)
        {
            bookcomments comment=new bookcomments();
            comment.setisbn(isbn);
            comment.setintroduction(intro);
            bookdao.editcomments(comment);
        }
        else
        {
            bookcomments comment=comments.get(0);
            comment.setintroduction(intro);
            bookdao.editcomments(comment);
        }
        return 0;

    }

    @Override
    public Object findimg(String isbn) {
        if(bookdao.getcommentbyisbn(isbn).size()>0)
        {
            System.out.println("find");
            return bookdao.getcommentbyisbn(isbn).get(0).getContent().getData();

        }

        return null;
    }
    @Override
    public Object addcomment(String addcomment,String isbn){
        List<bookcomments> comments=bookdao.getcommentbyisbn(isbn);
        System.out.println("success1");
        if(comments.size()==0)
        {
            bookcomments comment=new bookcomments();
            comment.setisbn(isbn);
            List<String> newcomments = new LinkedList<String>();
            newcomments.add(addcomment);
            comment.setcomment(newcomments);
            bookdao.editcomments(comment);
        }
        else
        {
            System.out.println("success2");
            bookcomments comment=comments.get(0);
            System.out.println("success3");
            List<String> newcomments=comment.getComments();
            if(newcomments==null)
            {
                newcomments=new LinkedList<String>();
            }
            newcomments.add(addcomment);
            comment.setcomment(newcomments);
            System.out.println("success4");
            bookdao.editcomments(comment);
        }
        return 0;
    }

}

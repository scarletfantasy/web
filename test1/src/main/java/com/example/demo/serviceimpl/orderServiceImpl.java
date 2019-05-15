package com.example.demo.serviceimpl;

import com.example.demo.dao.bookDao;
import com.example.demo.dao.historyDao;
import com.example.demo.dao.orderDao;
import com.example.demo.dao.userDao;
import com.example.demo.entity.Book;
import com.example.demo.entity.History;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class orderServiceImpl implements orderService {
    @Autowired
    bookDao bookdao;
    @Autowired
    orderDao orderdao;
    @Autowired
    userDao userdao;
    @Autowired
    historyDao historydao;
    @Override
    public Object addtocart(String id, String isbn,int number,String time) {
        if(id==null)
        {
            return "login";
        }

        Book book=bookdao.getbookbyid(isbn).get();
        User user=userdao.getuserbyid(id).get();


        Order order=new Order(time,number,book,user);
        orderdao.editorder(order);

        return "success";
    }

    @Override
    public Object cleancart(String id) {
        if(id==null)
        {
            return "login";
        }
        List<Order> cart=orderdao.getorderbyuserid(id);

        for(Order order:cart)
        {
            if(order.getbook().getnumber()>=order.getnumber())
            {
                History history=order.o2h();
                Book book=order.getbook();
                book.setnumber(book.getnumber()-order.getnumber());
                bookdao.editbook(book);
                historydao.edithistory(history);
                orderdao.deleteorder(order);
            }
        }
        orderdao.flush();
        return orderdao.getorderbyuserid(id);
    }

    @Override
    public Object showcart(String id) {
        if(id==null)
        {
            return "login";
        }
        List<Order> cart=orderdao.getorderbyuserid(id);
        System.out.println("success");
        return cart;
    }

    @Override
    public Object showhistory(String id) {
        if(id==null)
        {
            return "login";
        }
        if(id.equals("admin"))
        {
            return historydao.getallhistory();
        }
        List<History> cart=historydao.gethistorybyid(id);
        System.out.println("success");
        return cart;
    }

    @Override
    public Object deleteorder(String id) {
        if(id==null)
        {
            return "login";
        }
        Long lid=Long.parseLong(id);
        orderdao.deleteorder(orderdao.getbyid(lid));


        return "success";
    }
}

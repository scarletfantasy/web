package com.example.demo.daoimpl;

import com.example.demo.dao.orderDao;
import com.example.demo.entity.Order;
import com.example.demo.repo.bookRepo;
import com.example.demo.repo.orderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class orderDaoImpl implements orderDao {
    @Autowired
    orderRepo orderrepo;
    @Autowired
    bookRepo bookrepo;
    @Override
    public List<Order> getallorder()
    {
        return orderrepo.findAll();
    }
    @Override
    public void deleteorder(Order order)
    {
        orderrepo.delete(order);
    }
    @Override
    public void editorder(Order order)
    {
        orderrepo.save(order);
    }
    @Override
    public List<Order> getorderbyuserid(String id)
    {
        return orderrepo.getorderbyid(id);
    }
    @Override
    public void flush()
    {
        orderrepo.flush();
    }
    @Override
    public Order getbyid(Long id)
    {
        List<Order> cart=orderrepo.findAll();
        for(Order order:cart)
        {
            if(order.getid().equals(id))
            {
                return order;
            }
        }
        Order order=new Order();
        return order;
    }
    @Override
    public void deletorderbybook(String isbn)
    {
        List<Order> cart=orderrepo.findAll();
        for(Order order:cart)
        {
            if(order.getbook().getisbn().equals(isbn))
            {
                orderrepo.delete(order);
            }
        }
    }


}

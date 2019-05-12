package com.example.demo.dao;

import com.example.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.repo.*;

import java.util.List;
import java.util.Optional;

@Repository
public class orderDao {
    @Autowired
    orderRepo orderrepo;
    public List<Order> getallorder()
    {
        return orderrepo.findAll();
    }
    public void deleteorder(Order order)
    {
        orderrepo.delete(order);
    }
    public void editorder(Order order)
    {
        orderrepo.save(order);
    }

    public List<Order> getorderbyuserid(String id)
    {
        return orderrepo.getorderbyid(id);
    }
    public void flush()
    {
        orderrepo.flush();
    }
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


}

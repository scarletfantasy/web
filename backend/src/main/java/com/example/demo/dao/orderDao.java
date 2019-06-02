package com.example.demo.dao;

import com.example.demo.entity.Order;

import java.util.List;

public interface orderDao {
    public List<Order> getallorder();
    public void deleteorder(Order order);
    public void editorder(Order order);
    public void deletorderbybook(String isbn);
    public List<Order> getorderbyuserid(String id);
    public void flush();
    public Order getbyid(Long id);

}

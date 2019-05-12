package com.example.demo.dao;

import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repo.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class historyDao {
    @Autowired
    historyRepo historyrepo;
    public void edithistory(History history)
    {
        historyrepo.save(history);
    }
    public void deletehistory(History history)
    {
        historyrepo.delete(history);
    }
    public List<History> getallhistory()
    {
        return historyrepo.findAll();
    }
    public void flush()
    {
        historyrepo.flush();
    }
    public List<History> gethistorybyid(String id)
    {
        return historyrepo.gethistorybyid(id);

    }
    public Optional<History> getbyid(String id)
    {
        return historyrepo.findById(id);
    }


}

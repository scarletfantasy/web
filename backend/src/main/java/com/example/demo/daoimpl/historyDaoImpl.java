package com.example.demo.daoimpl;

import com.example.demo.dao.historyDao;
import com.example.demo.entity.History;
import com.example.demo.repo.historyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public class historyDaoImpl implements historyDao {
    @Autowired
    historyRepo historyrepo;
    @Override
    public void edithistory(History history)
    {
        historyrepo.save(history);
    }
    @Override
    public void deletehistory(History history)
    {
        historyrepo.delete(history);
    }
    @Override
    public List<History> getallhistory()
    {
        return historyrepo.findAll();
    }
    @Override
    public void flush()
    {
        historyrepo.flush();
    }
    @Override
    public List<History> gethistorybyid(String id)
    {
        return historyrepo.gethistorybyid(id);

    }
    @Override
    public Optional<History> getbyid(String id)
    {
        return historyrepo.findById(id);
    }


}

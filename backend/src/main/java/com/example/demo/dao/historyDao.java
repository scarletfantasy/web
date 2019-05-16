package com.example.demo.dao;

import com.example.demo.entity.History;

import java.util.List;
import java.util.Optional;

public interface historyDao {
    public void edithistory(History history);
    public void deletehistory(History history);
    public List<History> getallhistory();
    public void flush();
    public List<History> gethistorybyid(String id);
    public Optional<History> getbyid(String id);
}

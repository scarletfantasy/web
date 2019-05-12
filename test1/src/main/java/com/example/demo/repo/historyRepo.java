package com.example.demo.repo;

import com.example.demo.entity.Book;
import com.example.demo.entity.History;
import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface historyRepo extends JpaRepository<History, String>{
    @Query("SELECT b from History b")
    List<History> getAllHistory();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update history set isfinish=1 where id=?1",nativeQuery = true)
    int updateHistory(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from history where id=?1",nativeQuery = true)
    int deleteHistory(String id);

    @Transactional
    @Modifying
    @Query(value="insert into history(number,price,time,userid,isbn,isfinish) values(?1,?2,?3,?4,?5,0)",nativeQuery = true)
    int insertHistory(int number,double price,String time,String userid,String isbn);

    @Query("SELECT b from History b where b.userid=?1")
    List<History> gethistorybyid(String userid);






}
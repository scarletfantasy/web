package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface userRepo extends JpaRepository<User, String>{
    @Query("SELECT b from User b")
    List<User> getAllUser();
    @Transactional
    @Modifying
    @Query(value="insert into user(id,email,password) VALUES(?1,?3,?2)",nativeQuery = true)
    int insertUser(String id,String password,String email);


}

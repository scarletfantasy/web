package com.example.demo.repo;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface orderRepo extends JpaRepository<Order, String> {
   @Query("SELECT b from Order b where b.user.id=?1")
   List<Order> getorderbyid(String userid);

}

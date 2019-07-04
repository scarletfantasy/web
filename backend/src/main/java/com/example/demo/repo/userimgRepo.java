package com.example.demo.repo;

import com.example.demo.entity.Userimage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface userimgRepo extends MongoRepository<Userimage,String> {

    List<Userimage> findByUserid(@Param("userid") String userid);

    void deleteByUserid(@Param("userid") String userid);


}

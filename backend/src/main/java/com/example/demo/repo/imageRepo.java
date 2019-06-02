package com.example.demo.repo;

import com.example.demo.entity.bookimage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface imageRepo extends MongoRepository<bookimage,String> {



}

package com.example.demo.repo;

import com.example.demo.entity.bookimage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface imageRepo extends MongoRepository<bookimage,String> {
            List<bookimage> findByIsbn(@Param("isbn") String isbn);
}

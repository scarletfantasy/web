package com.example.demo.repo;

import com.example.demo.entity.bookcomments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface commentsRepo extends MongoRepository<bookcomments,String> {



}

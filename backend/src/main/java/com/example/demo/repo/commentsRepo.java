package com.example.demo.repo;

import com.example.demo.entity.bookcomments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface commentsRepo extends MongoRepository<bookcomments,String> {

    List<bookcomments> findByIsbn(@Param("isbn") String isbn);

}

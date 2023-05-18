package com.example.demo.DataBase;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PRRObjectRepository extends MongoRepository<PRRObject, String> {
    @Query("{ 'url' : 1, 'Rank' : 1 }")
    List<PRRObject> findAllByRank();
    long count();
}

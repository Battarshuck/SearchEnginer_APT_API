package com.example.demo.DataBase;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends MongoRepository<Samples, String> {
    @Query(value = "{ 'word' : ?0 }")
    List<Samples> findByWord(String word);

    List<Samples> findByWordIn(List<String> words);

//    @Query(value = "{title:'BBC - Homepage'}",fields = "{ 'url' : 1, 'body' : 1, 'h1' : 1, 'h2' : 1}")
//    List<Samples> findAll(String temp);

    public long count();
}

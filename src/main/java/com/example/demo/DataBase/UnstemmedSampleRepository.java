package com.example.demo.DataBase;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnstemmedSampleRepository extends MongoRepository<UnstemmedSamples, String> {

    List<UnstemmedSamples> findByWordIn(List<String> words);

    long count();
}

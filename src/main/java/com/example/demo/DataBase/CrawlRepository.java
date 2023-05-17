package com.example.demo.DataBase;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlRepository extends MongoRepository<Crawls, String> {
    public long count();
}

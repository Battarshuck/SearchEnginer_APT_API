package com.example.demo.Websites;

import com.example.demo.DataBase.SampleRepository;
import com.example.demo.DataBase.Samples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WebSiteService {

    SampleRepository sampleRepo;
    @Autowired
    public WebSiteService(SampleRepository sampleRepo) {
        this.sampleRepo = sampleRepo;
    }

    public List<Samples> findByWordIn(String words) {
        List<String> listsWords = List.of(words.split("[,!.+/ ]+"));
        //STEM-ING LOGIC
        return sampleRepo.findByWordIn(listsWords);
    }

    public long count() {
        return sampleRepo.count();
    }
}

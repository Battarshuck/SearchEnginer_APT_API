package com.example.demo.Websites;

import com.example.demo.DataBase.SampleRepository;
import com.example.demo.DataBase.Samples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import com.example.demo.WordProcessor.WordProcessor;

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
        WordProcessor wp = new WordProcessor();
        for (int i = 0; i < listsWords.size(); i++) { //for each word in the list
            listsWords.set(i, listsWords.get(i).replaceAll("[^a-zA-Z]", "")); //only allowing alphanumerics
            listsWords.set(i,wp.processWord(listsWords.get(i))); //setting word in list with the stem of the words 
        }
        return sampleRepo.findByWordIn(listsWords);
    }

    public long count() {
        return sampleRepo.count();
    }
}

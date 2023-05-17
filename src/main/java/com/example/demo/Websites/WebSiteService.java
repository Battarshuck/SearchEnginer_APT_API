package com.example.demo.Websites;

import com.example.demo.DataBase.CrawlRepository;
import com.example.demo.DataBase.SampleRepository;
import com.example.demo.DataBase.Samples;
import com.example.demo.Ranker.Ranker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import com.example.demo.WordProcessor.WordProcessor;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebSiteService {

    SampleRepository sampleRepo;
    CrawlRepository crawlRepo;

    @Autowired
    public WebSiteService(SampleRepository sampleRepo, CrawlRepository crawlRepo) {
        this.sampleRepo = sampleRepo;
        this.crawlRepo = crawlRepo;
    }

    public List<Website> findByWordIn(String words) {

        List<String> listsWords = List.of(words.split("[,!.+/ ]+"));
        List<String> stemmedList = new ArrayList<String>();
        WordProcessor wp = new WordProcessor();
        String stemmedWord;
       for (int i = 0; i < listsWords.size(); i++) { //for each word in the list
           //stemmedWord = listsWords.get(i).replaceAll("[^a-zA-Z]", "");
           stemmedWord = wp.processWord(listsWords.get(i));
           if (stemmedWord != null && !stemmedWord.equals(""))
                stemmedList.add(stemmedWord);
               
       }
        return Ranker.rank(sampleRepo.findByWordIn(stemmedList), this.crawlRepo.count());
    }

    public long count() {
        return sampleRepo.count();
    }

}

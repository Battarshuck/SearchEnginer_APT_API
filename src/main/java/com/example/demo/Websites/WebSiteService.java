package com.example.demo.Websites;

import com.example.demo.DataBase.*;
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
    UnstemmedSampleRepository unstemmedSampleRepository;

    PRRObjectRepository prrObjectRepository;

    Ranker ranker;

    @Autowired
    public WebSiteService(SampleRepository sampleRepo, CrawlRepository crawlRepo, UnstemmedSampleRepository unstemmedSampleRepository, PRRObjectRepository prrObjectRepository) {
        this.sampleRepo = sampleRepo;
        this.crawlRepo = crawlRepo;
        this.unstemmedSampleRepository = unstemmedSampleRepository;
        this.prrObjectRepository = prrObjectRepository;

        List<PRRObject> temp =  prrObjectRepository.findAllByRank();
        this.ranker = new Ranker(temp);
    }

    public List<Website> findByWordIn(String words, boolean exact) {

        if (exact) {
            String word = words.substring(1, words.length() - 1);
            List<String> listOfExactWords = List.of(word.split("[,!.+/ ]+"));
            return ranker.rank(unstemmedSampleRepository.findByWordIn(listOfExactWords), this.crawlRepo.count(), exact);
        }


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
        return ranker.rank(sampleRepo.findByWordIn(stemmedList), this.crawlRepo.count());
    }

    public long count() {
        return sampleRepo.count();
    }

}

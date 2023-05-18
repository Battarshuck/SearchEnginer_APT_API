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
    public WebSiteService(SampleRepository sampleRepo, CrawlRepository crawlRepo,
            UnstemmedSampleRepository unstemmedSampleRepository, PRRObjectRepository prrObjectRepository) {
        this.sampleRepo = sampleRepo;
        this.crawlRepo = crawlRepo;
        this.unstemmedSampleRepository = unstemmedSampleRepository;
        this.prrObjectRepository = prrObjectRepository;

        List<PRRObject> temp = prrObjectRepository.findAllByRank();
        this.ranker = new Ranker(temp);
    }

    public List<Website> findByWordIn(String words, boolean exact) {

        if (exact) {
            String word = words.substring(1, words.length() - 1);
            List<String> listOfExactWords = List.of(word.split("[,!.+/ ]+"));
            List<UnstemmedSamples> tempo = unstemmedSampleRepository.findByWordIn(listOfExactWords);
            // we need to match urls with each other
            // then check if indices are consecutive
            // then return the list of websites
            // ArrayList<UnstemmedSamples> exacList = new ArrayList<UnstemmedSamples>();
            // List<List<URLS>> urlsList = new ArrayList<List<URLS>>();
            // for (int i = 0; i < tempo.size(); i++) {
            // // urlsList.set(i, tempo.get(i).getUrls());
            // urlsList.add(tempo.get(i).getUrls());
            // }
            // bool alltrue = false;
            // ArrayList<Boolean> Flags = new ArrayList<Boolean>(urlsList.size() - 1);//
            // Boolean array for each
            // // Here was a for loop from 0->urlsList.size()-1 but i removed it on the
            // // assumption that if the URL is consistent with the UnstemmedSample, then it
            // // will be consistent with the rest.

            // for (int j = 1; j < urlsList.size(); j++) {
            // for (int k = 0; k < urlsList.get(0).size(); k++) {
            // for (int l = 0; l < urlsList.get(j).size(); l++) {
            // if (urlsList.get(i).get(k).getUrl().equals(urlsList.get(j).get(l).getUrl()))
            // {
            // for (int m = 0; m < urlsList.get(0).get(k).getIndices().size(); m++) {
            // for (int n = 0; n < urlsList.get(j).get(l).getIndices().size(); n++) {
            // if (urlsList.get(0).get(k).getIndices().get(m) + j == urlsList.get(j).get(l)
            // .getIndices().get(n)) {
            // Flags.set(j - 1, true);// set the condition of indices between 1 and j to
            // true
            // }
            // }
            // }
            // for (int test = 0; test < Flags.size(); test++) {
            // if (Flags.get(test) == true) {
            // alltrue = true;
            // } else {
            // alltrue = false;
            // break;
            // }
            // }
            // if (alltrue == true) {
            // exacList.add(urlsList.get(0).get(k).getUrl());
            // }
            // }
            // }
            // }
            // }
            return ranker.rank(unstemmedSampleRepository.findByWordIn(listOfExactWords), this.crawlRepo.count(), exact);
        }
        List<String> listsWords = List.of(words.split("[,!.+/ ]+"));
        List<String> stemmedList = new ArrayList<String>();
        WordProcessor wp = new WordProcessor();
        String stemmedWord;
        for (int i = 0; i < listsWords.size(); i++) { // for each word in the list
            // stemmedWord = listsWords.get(i).replaceAll("[^a-zA-Z]", "");
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

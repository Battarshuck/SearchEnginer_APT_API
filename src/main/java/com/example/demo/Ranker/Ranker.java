package com.example.demo.Ranker;

import com.example.demo.DataBase.CrawlRepository;
import com.example.demo.DataBase.SampleRepository;
import com.example.demo.DataBase.Samples;
import com.example.demo.DataBase.URLS;
import com.example.demo.Websites.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


public class Ranker {

    public static final int MAX_RESULTS = 250;
    public static List<Website> rank(List<Samples> words, double totalDocs)
    {
        HashMap<String, Website> ranks = new HashMap<>();

        for (Samples word : words) {
            //calculate idf
            double idf = Math.log(totalDocs / word.getUrls().size());
            for (URLS url : word.getUrls()) {
                //inserting the object to the hashmap
                //check if already exists
                if (ranks.containsKey(url.getUrl()))
                    ranks.get(url.getUrl()).addToRank(idf * url.getTf());
                 else
                    ranks.put(url.getUrl(), new Website(idf*url.getTf(), url.getUrl(), url.getContent(), url.getTitle()));

            }
        }
        //convert to list
        List<Website> rankedList = new java.util.ArrayList<>(ranks.values());
        rankedList.sort(Collections.reverseOrder());//sort by rank the highest first
        //return the first 300 results
        return rankedList.subList(0, Math.min(rankedList.size(), MAX_RESULTS));
    }
}

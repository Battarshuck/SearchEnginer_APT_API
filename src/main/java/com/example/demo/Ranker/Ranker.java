package com.example.demo.Ranker;

import com.example.demo.DataBase.*;
import com.example.demo.Websites.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


public class Ranker {

    public static final int MAX_RESULTS = 250;
    private HashMap<String, Double> popularity;
    public Ranker(List<PRRObject> prrObjects) {

        //put every url as key and the popularity as value
        //it is a preprocessing step
        popularity = new HashMap<>();
        for (PRRObject prrObject : prrObjects) {
            popularity.put(prrObject.getUrl(), prrObject.getRank());
        }

    }

    public  List<Website> rank(List<Samples> words, double totalDocs)
    {
        HashMap<String, Website> ranks = new HashMap<>();

        for (Samples word : words) {
            //calculate idf
            double idf = Math.log(totalDocs / word.getUrls().size());
            for (URLS url : word.getUrls()) {
                //inserting the object to the hashmap
                //check if already exists
                if (ranks.containsKey(url.getUrl()))
                    ranks.get(url.getUrl()).addToRank(idf * url.getTf()*1.2);
                 else
                    ranks.put(url.getUrl(), new Website(idf*url.getTf()*1.2, url.getUrl(), url.getContent(), url.getTitle()));

            }
        }
        //convert to list
        List<Website> rankedList = new java.util.ArrayList<>(ranks.values());
        //loop over the list and add the popularity
        for (Website website : rankedList) {
            if (popularity.containsKey(website.getUrl()))
                website.addToRank(0.35*popularity.get(website.getUrl()));
        }
        //sort by rank the highest first
        rankedList.sort(Collections.reverseOrder());
        //return the first 300 results
        return rankedList.subList(0, Math.min(rankedList.size(), MAX_RESULTS));
    }

    //for exact search
    public  List<Website> rank(List<UnstemmedSamples> words, double totalDocs, boolean unstemmed)
    {
        HashMap<String, Website> ranks = new HashMap<>();

        for (UnstemmedSamples word : words) {
            //calculate idf
            double idf = Math.log(totalDocs / word.getUrls().size());
            for (URLS url : word.getUrls()) {
                //inserting the object to the hashmap
                //check if already exists
                if (ranks.containsKey(url.getUrl()))
                    ranks.get(url.getUrl()).addToRank(idf * url.getTf()*1.2);
                else
                    ranks.put(url.getUrl(), new Website(idf*url.getTf()*1.2, url.getUrl(), url.getContent(), url.getTitle()));

            }
        }
        //convert to list
        List<Website> rankedList = new java.util.ArrayList<>(ranks.values());
        //loop over the list and add the popularity
        for (Website website : rankedList) {
            if (popularity.containsKey(website.getUrl()))
                website.addToRank(0.35*popularity.get(website.getUrl()));
        }
        //sort by rank the highest first
        rankedList.sort(Collections.reverseOrder());
        //return the first 300 results
        return rankedList.subList(0, Math.min(rankedList.size(), MAX_RESULTS));
    }
}

package com.example.demo.DataBase;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "indexedCollection")
public class Samples {
    @Id
    private String id;
    String word;
    List<URLS> urls;

    public Samples(String word, List<URLS> urls) {
        this.word = word;
        this.urls = urls;
    }

    public String getWord() {
        return word;
    }

    public List<URLS> getUrls() {
        return urls;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setUrls(List<URLS> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "Samples{" +
                "id='" + id + '\'' +
                ", word='" + word + '\'' +
                ", urls=" + urls +
                '}';
    }

}

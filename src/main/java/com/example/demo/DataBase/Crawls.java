package com.example.demo.DataBase;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mainCrawler")
public class Crawls {
    @Id
    private String id;
    private String url;
    private String title;
    private String body;
    private String h1;
    private String h2;

    public Crawls(String url, String title, String body, String h1, String h2) {
        this.url = url;
        this.title = title;
        this.body = body;
        this.h1 = h1;
        this.h2 = h2;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getH1() {
        return h1;
    }

    public String getH2() {
        return h2;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    @Override
    public String toString() {
        return "Crawls{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", h1='" + h1 + '\'' +
                ", h2='" + h2 + '\'' +
                '}';
    }


}

package com.example.demo.DataBase;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PageRank")
public class PRRObject {
    @Id
    private String id;
    private String url;
    private double Rank;

    public PRRObject(String url, double rank) {
        this.url = url;
        this.Rank = rank;
    }

    public String getUrl() {
        return url;
    }

    public double getRank() {
        return Rank;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRank(double rank) {
        this.Rank = rank;
    }

    @Override
    public String toString() {
        return "PRRObject{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", rank=" + Rank +
                '}';
    }
}

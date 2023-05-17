package com.example.demo.Websites;

public class Website implements Comparable<Website>{

    private double rank;
    private String url;
    private String content;
    private String title;

    public Website(double rank, String url, String content, String title) {
        this.url = url;
        this.content = content;
        this.title = title;
        this.rank = rank;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRank() {
        return rank;
    }

    public void addToRank(double rank) {
        this.rank += rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }


    @Override
    public String toString() {
        return "Website{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int compareTo(Website o) {
        return Double.compare(this.rank, o.rank);
    }
}

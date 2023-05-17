package com.example.demo.DataBase;

import java.util.List;

public class URLS {

    String url;
    String frequency;
    double tf;
    boolean inTitle;
    boolean inH1;
    boolean inH2;
    String content;
    String title;
    List<Integer> indices;

    public URLS(String url, String frequency, double tf, boolean inTitle, boolean inH1, boolean inH2, String content, String title, List<Integer> indices) {
        this.url = url;
        this.frequency = frequency;
        this.tf = tf;
        this.inTitle = inTitle;
        this.inH1 = inH1;
        this.inH2 = inH2;
        this.content = content;
        this.title = title;
        this.indices = indices;
    }

    //setters and getters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getTf() {
        return tf;
    }

    public void setTf(double tf) {
        this.tf = tf;
    }

    public boolean isInTitle() {
        return inTitle;
    }

    public void setInTitle(boolean inTitle) {
        this.inTitle = inTitle;
    }

    public boolean isInH1() {
        return inH1;
    }

    public void setInH1(boolean inH1) {
        this.inH1 = inH1;
    }

    public boolean isInH2() {
        return inH2;
    }

    public void setInH2(boolean inH2) {
        this.inH2 = inH2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    @Override
    public String toString() {
        return "URLS{" +
                "url='" + url + '\'' +
                ", frequency='" + frequency + '\'' +
                ", tf='" + tf + '\'' +
                ", inTitle=" + inTitle +
                ", inH1=" + inH1 +
                ", inH2=" + inH2 +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", indices=" + indices +
                '}';
    }



}

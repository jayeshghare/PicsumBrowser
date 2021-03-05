package com.example.project;

public class Data {
    private String url;
    private String author;
    public Data() { }

    public Data(String url, String author) {
        this.url = url;
        this.author = author;
    }

    public String getUrl() {
        String s = "https://picsum.photos/300/300?image=" + url;
        return s;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

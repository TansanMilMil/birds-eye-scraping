package com.serverless.SiteScraping;

public class News {
    public long id;
    public String data;
    public String sourceBy;

    public News(long id, String data, String sourceBy) {
        this.id = id;
        this.data = data;
        this.sourceBy = sourceBy;
    }
}

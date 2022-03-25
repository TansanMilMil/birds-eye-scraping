package com.serverless.SiteScraping;

public class News {
    public long id;
    public String title;
    public String description;
    public String sourceBy;
    public String scrapedUrl;
    public String scrapedDateTime;
    public String articleUrl;

    public News(long id, String title, String description, String sourceBy, String scrapedUrl, String scrapedDateTime, String articleUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sourceBy = sourceBy;
        this.scrapedUrl = scrapedUrl;
        this.scrapedDateTime = scrapedDateTime;
        this.articleUrl = articleUrl;
    }
}

package com.serverless.SiteScraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.serverless.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeCloudWatchImpress implements ScrapingBase {
    private final Logger LOG = LogManager.getLogger(Handler.class);
    private final String SOURCE_BY = "cloudWatchImpress";
    private final String SOURCE_URL = "https://cloud.watch.impress.co.jp/";

    public String getSourceBy() {
        return SOURCE_BY;
    }

    public List<News> extractNews() throws IOException {
        List<News> newsList = new ArrayList<News>();

        // jsoupで解析
        Document doc = Jsoup.connect(SOURCE_URL).get();
        Elements newsAreaList = doc.select("#main > article > aside.top-news.topics > div > ul > li.item.news");
        int id = 0;
        for (Element newsArea : newsAreaList) {
            id++;
            Elements newsTitle = newsArea.select("div.body > div.text > p.title");
            newsList.add(new News(id, newsTitle.html(), null, SOURCE_BY));
        }

        return newsList;
    }    
}

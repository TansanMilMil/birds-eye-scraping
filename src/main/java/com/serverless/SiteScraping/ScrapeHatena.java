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

public class ScrapeHatena implements ScrapingBase {
    private final Logger LOG = LogManager.getLogger(Handler.class);
    private final String SOURCE_BY = "hatena";
    private final String SOURCE_URL = "https://b.hatena.ne.jp/hotentry/it";

    public String getSourceBy() {
        return SOURCE_BY;
    }

    public List<News> extractNews() throws IOException {
        List<News> newsList = new ArrayList<News>();

        // jsoupで解析
        Document doc = Jsoup.connect(SOURCE_URL).get();
        Elements newsAreaList = doc.select("#container > div.wrapper > div > div.entrylist-main > section > ul > li");
        int id = 0;
        for (Element newsArea : newsAreaList) {
            id++;
            Elements newsTitle = newsArea.select("div > div.entrylist-contents-main > h3");
            newsList.add(new News(id, newsTitle.html(), null, SOURCE_BY));                    
        }

        return newsList;
    }    
}

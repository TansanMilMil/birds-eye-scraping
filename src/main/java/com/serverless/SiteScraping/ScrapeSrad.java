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

public class ScrapeSrad implements ScrapingBase {
    private final Logger LOG = LogManager.getLogger(Handler.class);
    private final String SOURCE_BY = "srad";
    private final String SOURCE_URL = "https://srad.jp/";

    public String getSourceBy() {
        return SOURCE_BY;
    }

    public List<News> extractNews() throws IOException {
        List<News> newsList = new ArrayList<News>();

        // jsoupで解析
        Document doc = Jsoup.connect(SOURCE_URL).get();
        Elements newsAreaList = doc.select("#firehoselist > article");
        int id = 0;
        for (Element newsArea : newsAreaList) {
            id++;
            Elements newsTitle = newsArea.select("header > h2.story > span[id^=\"title\"]");
            Elements newsDescription = newsArea.select("div.body > div");
            newsList.add(new News(id, newsTitle.html(), newsDescription.html(), SOURCE_BY));                    
        }

        return newsList;
    }    
}

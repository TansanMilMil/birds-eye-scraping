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

public class ScrapeZDNet implements ScrapingBase {
    private final Logger LOG = LogManager.getLogger(Handler.class);
    private final String SOURCE_BY = "zdnet";
    private final String SOURCE_URL = "https://japan.zdnet.com/";

    public String getSourceBy() {
        return SOURCE_BY;
    }

    public List<News> extractNews() throws IOException {
        List<News> newsList = new ArrayList<News>();

        // jsoupで解析
        Document doc = Jsoup.connect(SOURCE_URL).get();
        Elements newsAreaList = doc.select("#page-wrap > div.pg-container-main > main > section:nth-child(1) > div > ul > li");
        int id = 0;
        for (Element newsArea : newsAreaList) {
            id++;
            String href = SOURCE_URL + newsArea.select("a").attr("href");
            String newsTitle = newsArea.select("a > div.txt > p.txt-ttl").text();
            newsList.add(new News(id, "<a href=\"" + href + "\">" + newsTitle +"</a>", null, SOURCE_BY));
        }

        return newsList;
    }            
}

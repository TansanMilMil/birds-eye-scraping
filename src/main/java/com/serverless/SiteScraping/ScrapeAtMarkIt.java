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

public class ScrapeAtMarkIt implements ScrapingBase {
    private static final Logger LOG = LogManager.getLogger(Handler.class);
    private static final String SOURCE_BY = "atMarkItNews";

    public String getSourceBy() {
        return SOURCE_BY;
    }

    public List<News> extractNews() throws IOException {
        List<News> newsList = new ArrayList<News>();

        // jsoupで解析
        Document doc = Jsoup.connect("https://atmarkit.itmedia.co.jp/ait/subtop/news/").get();
        Elements newsAreaList = doc.select("#subtopContents > div:nth-child(3) > div > div.colBoxInner > div");
        int id = 0;
        for (Element newsArea : newsAreaList) {
            id++;
            Elements newsTitle = newsArea.select("div.colBoxTitle > h3");
            Elements newsDescription = newsArea.select("div.colBoxDescription > p");
            LOG.info(newsTitle.html() + "\n");
            newsList.add(new News(id, newsTitle.html(), newsDescription.html(), SOURCE_BY));                    
        }

        return newsList;
    }
}

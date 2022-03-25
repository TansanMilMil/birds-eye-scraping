package com.serverless.SiteScraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.serverless.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScrapeHatena implements ScrapingBase {
    private final Logger LOG = LogManager.getLogger(Handler.class);
    private final String SOURCE_BY = "hatena";
    private final String SOURCE_URL = "https://b.hatena.ne.jp/hotentry/it";

    @Override
    public String getSourceBy() {
        return SOURCE_BY;
    }

    @Override
    public List<News> extractNews() throws IOException {
        List<News> newsList = new ArrayList<News>();

        // jsoupで解析
        Document doc = Jsoup.connect(SOURCE_URL).get();
        Elements newsAreaList = doc.select("#container > div.wrapper > div > div.entrylist-main > section > ul > li");
        int id = 0;
        for (Element newsArea : newsAreaList) {
            id++;
            Elements newsTitle = newsArea.select("div > div.entrylist-contents-main > h3 > a");
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
            String nowString = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            newsList.add(new News(id, newsTitle.text(), null, SOURCE_BY, SOURCE_URL, nowString, newsTitle.attr("href")));                    
        }

        return newsList;
    }    
}

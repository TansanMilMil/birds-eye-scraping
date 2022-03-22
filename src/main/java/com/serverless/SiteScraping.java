package com.serverless;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SiteScraping {
    private static final Logger LOG = LogManager.getLogger(Handler.class);
    
    public static void Scrape() throws IOException {
        Document doc = Jsoup.connect("https://www.itmedia.co.jp/news/").get();
        Elements newsHeadlines = doc.select("#NewArt > div > div.colBoxInner");
        for (Element headline : newsHeadlines) {
            LOG.info("%s\n\t", headline.select("div:nth-child(1) > div.colBoxContents > div.colBoxTitle > h3 > a"));
        }
    }
}

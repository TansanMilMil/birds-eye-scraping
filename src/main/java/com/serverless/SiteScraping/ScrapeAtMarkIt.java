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

public class ScrapeAtMarkIt {
    private static final Logger LOG = LogManager.getLogger(Handler.class);

    public static List<String> extractNews() throws IOException {
        LOG.info("scrape atmarkit...");
        List<String> list = new ArrayList<String>();

        // jsoupで解析
        Document doc = Jsoup.connect("https://atmarkit.itmedia.co.jp/ait/subtop/news/").get();
        Elements headlines = doc.select("#subtopContents > div:nth-child(3) > div.colBoxOuter > div.colBoxInner");
        for (Element headline : headlines) {
            String newsLink = headline.select("div.colBoxTitle > h3").html();
            LOG.info(newsLink + "\n");
            list.add(newsLink);
        }

        return list;
    }
}

package com.serverless.SiteScraping;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.Handler;
import com.serverless.AwsS3.S3Manager;
import com.serverless.Encode.EncodeString;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SiteScraping {
    private static final Logger LOG = LogManager.getLogger(Handler.class);
    private static final int MAX_SCRAPING_ARTICLE = 5;
    
    public static void scrape() throws IOException {
        List<ScrapingBase> scrapingList = Arrays.asList(
            new ScrapeAtMarkIt(),
            new ScrapeCloudWatchImpress(),
            new ScrapeHatena(),
            new ScrapeZenn(),
            new ScrapeSrad(),
            new ScrapeZDNet()
        );
        for (ScrapingBase scraping : scrapingList) {
            List<News> newsList = scraping.extractNews();
            putToS3(newsList, scraping.getSourceBy());
            LOG.info(scraping.getSourceBy() + " / scraped article: " + newsList.size());
        }
    }

    private static void putToS3(List<News> newsList, String sourceBy) throws IOException {
        S3Manager s3Manager = new S3Manager();
        String bucketName = "birds-eye-news"; 
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
                
        for (int i = 0; i < newsList.size() && i < MAX_SCRAPING_ARTICLE; i++) {
            s3Manager.putObject(
                now.format(DateTimeFormatter.ISO_LOCAL_DATE) + "/" + sourceBy + "/" + newsList.get(i).id + ".json", 
                bucketName, 
                EncodeString.toByteBuffer(toJsonString(newsList.get(i)))
            );
        }
    }

    private static String toJsonString(News news) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(news);
        return jsonStr;
    }
}

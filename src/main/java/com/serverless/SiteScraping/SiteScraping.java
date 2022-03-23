package com.serverless.SiteScraping;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    
    public static void scrape() throws IOException {
        List<String> atMarkItNews = ScrapeAtMarkIt.extractNews();
        putToS3(atMarkItNews, "atMarkItNews");
    }

    private static void putToS3(List<String> newsList, String sourceBy) throws IOException {
        S3Manager s3Manager = new S3Manager();
        String bucketName = "birds-eye-news"; 
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
                
        for (int i = 0; i < newsList.size(); i++) {
            long id = i + 1;
            News news = new News(id, newsList.get(0), sourceBy);
            String jsonStr = toJsonString(news);
            s3Manager.putObject(
                now.format(DateTimeFormatter.ISO_LOCAL_DATE) + "/" + sourceBy + "/" + id + ".json", 
                bucketName, 
                EncodeString.toByteBuffer(jsonStr)
            );
        }
    }

    private static String toJsonString(News news) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(news);
        return jsonStr;
    }
}

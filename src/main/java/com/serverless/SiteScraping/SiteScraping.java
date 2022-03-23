package com.serverless.SiteScraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.serverless.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SiteScraping {
    private static final Logger LOG = LogManager.getLogger(Handler.class);
    
    public static void scrape() throws IOException {
        List<String> atMarkItNews = ScrapeAtMarkIt.extractNews();
        for (String newsStr : atMarkItNews) {
            
        }

    }
}

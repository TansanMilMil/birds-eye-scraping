package com.serverless.SiteScraping;

import java.io.IOException;
import java.util.List;

public interface ScrapingBase {
    public abstract String getSourceBy();
    public abstract List<News> extractNews() throws IOException;
}

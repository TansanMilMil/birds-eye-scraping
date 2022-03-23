package com.serverless.SiteScraping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String data;
    private String type;

    public News(String data, String type) {
        this.data = data;
        this.type = type;
    }

    public String getId() {
        return data;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.project.url_shortening_service.model;

public class UrlMapping {
    private final String id;
    private final String url;
    private final String actualUrl;

    public UrlMapping(String id, String url, String actualUrl) {
        this.id = id;
        this.url = url;
        this.actualUrl = actualUrl;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getActualUrl() {
        return actualUrl;
    }
}

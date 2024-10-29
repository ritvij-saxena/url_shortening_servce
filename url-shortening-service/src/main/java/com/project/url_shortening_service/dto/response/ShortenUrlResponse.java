package com.project.url_shortening_service.dto.response;


public class ShortenUrlResponse {
    private String id;
    private String url;
    private String actualUrl;

    public ShortenUrlResponse(String id, String url, String actualUrl) {
        this.id = id;
        this.url = url;
        this.actualUrl = actualUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActualUrl() {
        return actualUrl;
    }

    public void setActualUrl(String actualUrl) {
        this.actualUrl = actualUrl;
    }
}

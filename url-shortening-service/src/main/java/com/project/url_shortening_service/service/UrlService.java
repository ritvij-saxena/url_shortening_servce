package com.project.url_shortening_service.service;

import com.project.url_shortening_service.dto.response.ShortenUrlResponse;
import com.project.url_shortening_service.model.UrlMapping;

public interface UrlService {
    String shortenUrl(String urlToShorten);
    String getActualUrl(String shortUrl);
    UrlMapping getMappingData(String shortUrl);
}

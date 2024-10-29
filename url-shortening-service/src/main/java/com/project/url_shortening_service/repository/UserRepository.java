package com.project.url_shortening_service.repository;

import com.project.url_shortening_service.model.UrlMapping;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final ConcurrentHashMap<String, UrlMapping> db = new ConcurrentHashMap<>();

    public void addUrlMapping(UrlMapping urlMapping) {
        db.put(urlMapping.getId(), urlMapping);
    }

    public UrlMapping getUrlMapping(String shortUrl) {
        return db.getOrDefault(shortUrl, null);
    }
}

package com.project.url_shortening_service.service.impl;
import com.project.url_shortening_service.exception.UrlNotFoundException;
import com.project.url_shortening_service.model.UrlMapping;
import com.project.url_shortening_service.repository.UserRepository;
import com.project.url_shortening_service.service.UrlService;
import com.project.url_shortening_service.util.Base62Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UrlService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String shortenUrl(String urlToShorten) {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 13);
        String id = Base62Encoder.getEncodedId(uuid);
        log.info("id created: " + id);
        UrlMapping urlMapping = new UrlMapping(id, "short.ly/" + id, urlToShorten);
        userRepository.addUrlMapping(urlMapping);
        return urlMapping.getUrl(); // Return the shortened URL
    }


    @Override
    public String getActualUrl(String shortUrl) {
        UrlMapping urlMapping = userRepository.getUrlMapping(shortUrl);
        if (urlMapping == null) {
            throw new UrlNotFoundException("URL not found for the given ID: " + shortUrl);
        }
        return urlMapping.getActualUrl();
    }

    @Override
    public UrlMapping getMappingData(String shortUrl) {
        return userRepository.getUrlMapping(shortUrl);
    }


}

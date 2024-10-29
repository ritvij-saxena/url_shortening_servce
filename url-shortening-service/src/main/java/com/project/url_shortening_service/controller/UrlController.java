package com.project.url_shortening_service.controller;

import com.project.url_shortening_service.dto.request.ShortenUrlRequest;
import com.project.url_shortening_service.dto.response.ShortenUrlResponse;
import com.project.url_shortening_service.exception.InvalidUrlInputException;
import com.project.url_shortening_service.model.UrlMapping;
import com.project.url_shortening_service.service.UrlService;
import com.project.url_shortening_service.validator.ValidHttpLink;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
@Slf4j
@Validated
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(
            @RequestParam(value = "url", required = false) String originalUrl,
            @RequestBody(required = false) @Valid ShortenUrlRequest shortenUrlRequest) {

        log.info("in POST/user/shorten");
        log.info("Incoming: " + "originalUrl="+originalUrl + " " +
                "shortenUrlRequest:" + shortenUrlRequest );

        // Check that either originalUrl or shortenUrlRequest is provided
        if ((originalUrl == null && shortenUrlRequest == null) ||
                (originalUrl != null && shortenUrlRequest != null)) {
            throw new InvalidUrlInputException("Provide either 'url' as a query parameter or " +
                    "in the request body, but not both.");
        }

        // Determine which URL to shorten
        String urlToShorten = originalUrl != null ? originalUrl : shortenUrlRequest.getUrl();
        log.info("urlToShorten: " + urlToShorten);
        // Validate the final URL to shorten
        if (originalUrl == null) {
            if (shortenUrlRequest == null || shortenUrlRequest.getUrl() == null) {
                throw new InvalidUrlInputException("URL to shorten must not be null.");
            }
            urlToShorten = shortenUrlRequest.getUrl();
        }

        String response = urlService.shortenUrl(urlToShorten);
        log.info("Response: " + response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shorten_url}")
    public ResponseEntity<String> getActualUrl(@PathVariable("shorten_url") String shortUrl) {
        String originalUrl = urlService.getActualUrl(shortUrl);
        return ResponseEntity.ok(originalUrl);
    }

    @GetMapping("/{shorten_url}/all")
    public ResponseEntity<ShortenUrlResponse> getAllMapping(@PathVariable("shorten_url") String shortUrl) {
        UrlMapping mapping = urlService.getMappingData(shortUrl);
        ShortenUrlResponse response = new ShortenUrlResponse(mapping.getId(), mapping.getUrl(), mapping.getActualUrl());
        return ResponseEntity.ok(response);
    }
}

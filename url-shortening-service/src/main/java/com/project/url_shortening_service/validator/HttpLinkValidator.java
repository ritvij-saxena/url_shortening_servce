package com.project.url_shortening_service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpLinkValidator implements ConstraintValidator<ValidHttpLink, String> {

    @Override
    public void initialize(ValidHttpLink constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false; // Null or empty value is invalid
        }
        try {
            URL url = new URL(value);
            return "http".equals(url.getProtocol()) || "https".equals(url.getProtocol());
        } catch (MalformedURLException e) {
            return false; // Invalid URL format
        }
    }
}

package com.project.url_shortening_service.exception;

public class InvalidUrlInputException extends RuntimeException {
    public InvalidUrlInputException(String message) {
        super(message);
    }
}

package com.project.url_shortening_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ShortenUrlRequest {

    @NotBlank(message = "URL must not be blank")
    private String url;
}

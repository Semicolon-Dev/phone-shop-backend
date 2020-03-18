package dev.semicolon.phoneshop.phone.services.fonoapi.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Data
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(value = "fonoapi", ignoreUnknownFields = false)
public class FonoApiConfig {

    private final String url;
    private final String token;

}

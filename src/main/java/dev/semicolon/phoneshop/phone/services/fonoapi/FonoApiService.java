package dev.semicolon.phoneshop.phone.services.fonoapi;

import dev.semicolon.phoneshop.phone.services.fonoapi.config.FonoApiConfig;
import dev.semicolon.phoneshop.phone.services.fonoapi.model.FonoPhone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FonoApiService {

    private final FonoApiConfig config;
    private final RestTemplate http;

    public List<FonoPhone> getLatest(String brand, int limit) throws Exception {
        Object requestBody = null;
        FonoPhone[] response = http.postForObject(url() + "/getlatest?token={token}&brand={brand}&limit={limit}",
                requestBody, FonoPhone[].class, token(), brand, limit);

        return List.of(Optional.ofNullable(response)
                .orElseThrow(() -> new Exception("FonoApi response was null")));
    }

    private String url() {
        return config.getUrl();
    }

    private String token() {
        return config.getToken();
    }

}

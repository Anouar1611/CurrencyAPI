package com.currencyapi.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${currencies.names.url}")
    private String CURRENCIES_API_URL;

    private final WebClient webClient;

    public CurrencyServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public Mono<Map<String, String>> getFullCurrencyNames() {
        return webClient.get()
                .uri(CURRENCIES_API_URL)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseCurrencyFullNames);
    }

    private Map<String, String> parseCurrencyFullNames(String responseBody) {
        Map<String, String> currencyFullNames = new HashMap<>();
        String[] pairs = responseBody.replaceAll("[{}\"]", "").split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                currencyFullNames.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return currencyFullNames;
    }
}


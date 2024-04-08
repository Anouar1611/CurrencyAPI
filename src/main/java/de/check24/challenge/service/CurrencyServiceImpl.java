package de.check24.challenge.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${currencies.names.url}")
    private String CURRENCIES_API_URL;

    @Override
    public Map<String, String> getFullCurrencyNames() {
        String responseBody = restTemplate.getForObject(CURRENCIES_API_URL, String.class);
        return parseCurrencyFullNames(responseBody);
    }

    private Map<String, String> parseCurrencyFullNames(String responseBody) {
        Map<String, String> currencyFullNames = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            jsonNode.fields().forEachRemaining(entry -> currencyFullNames.put(entry.getKey(), entry.getValue().asText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencyFullNames;
    }

}

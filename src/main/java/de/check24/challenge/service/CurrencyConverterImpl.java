package de.check24.challenge.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class CurrencyConverterImpl implements CurrencyConverter {

    @Autowired
    public RestTemplate restTemplate;

    @Value("${currency.api.url}")
    public String currencies;


    @Override
    public double convert(double amount, String targetCurrency) {

        String responseBody = restTemplate.getForObject(currencies, String.class);
        double conversionRate = parseConversionRate(responseBody, targetCurrency);
        return amount * conversionRate;
    }

    private double parseConversionRate(String responseBody, String targetCurrency) {
        double conversionRate = 0.0;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            JsonNode ratesNode = jsonNode.get("rates");
            JsonNode rateNode = ratesNode.get(targetCurrency);
            if (rateNode != null && rateNode.isNumber()) {
                conversionRate = rateNode.asDouble();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return conversionRate;
    }

}

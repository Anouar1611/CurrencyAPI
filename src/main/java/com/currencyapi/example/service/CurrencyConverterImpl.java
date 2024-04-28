package com.currencyapi.example.service;

import com.currencyapi.example.request.CurrencyRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class CurrencyConverterImpl implements CurrencyConverter {

    private final WebClient webClient;

    @Value("${currency.api.url}")
    public String currencies;

    public CurrencyConverterImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Override
    public Mono<Double> convert(double amount, String targetCurrency) {
        return this.webClient.get()
                .uri(currencies)
                .retrieve()
                .bodyToMono(CurrencyRates.class)
                .map(currencyRates -> currencyRates.getRates().getOrDefault(targetCurrency, 0.0) * amount);
    }

}




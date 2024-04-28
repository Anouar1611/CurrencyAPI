package com.currencyapi.example.service;

import reactor.core.publisher.Mono;

public interface CurrencyConverter {
    Mono<Double> convert(double amount, String targetCurrency);
}

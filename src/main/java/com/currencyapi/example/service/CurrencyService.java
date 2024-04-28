package com.currencyapi.example.service;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface CurrencyService {

    Mono<Map<String, String>> getFullCurrencyNames();
}
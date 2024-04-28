package com.currencyapi.example.web.controller;

import com.currencyapi.example.service.CurrencyConverter;
import com.currencyapi.example.service.CurrencyServiceImpl;
import com.currencyapi.example.request.ConvertRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class CurrencyConverterController {

    private final CurrencyConverter currencyConverter;

    private final CurrencyServiceImpl currencyServiceImpl;

    public CurrencyConverterController(
            CurrencyConverter currencyConverter,
            CurrencyServiceImpl currencyServiceImpl
    ) {
        this.currencyConverter = currencyConverter;
        this.currencyServiceImpl = currencyServiceImpl;
    }

    @PostMapping("/convert")
    public Mono<ResponseEntity<?>> convertEurAmount(@RequestBody ConvertRequest request) {
        try {
            double amount = Double.parseDouble(request.getAmountToConvert().toString());
            return currencyConverter.convert(amount, request.getTargetCurrency())
                    .map(ResponseEntity::ok);
        } catch (NumberFormatException | NullPointerException e) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be a number"));
        }
    }


    @Cacheable("currenciesCache")
    @GetMapping("/currencies")
    public Mono<Map<String, String>> getAllCurrenciesNames() {
        return currencyServiceImpl.getFullCurrencyNames();
    }

}
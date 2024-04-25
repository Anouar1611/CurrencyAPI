package com.currencyapi.example.web.controller;

import com.currencyapi.example.service.CurrencyConverter;
import com.currencyapi.example.service.CurrencyServiceImpl;
import com.currencyapi.example.request.ConvertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Double> convertEurAmount(
            @RequestBody ConvertRequest request
    ) {
        return ResponseEntity.ok(currencyConverter.convert(request.getAmountToConvert(), request.getTargetCurrency()));
    }


    @Cacheable("currenciesCache")
    @GetMapping("/currencies")
    public ResponseEntity<Map<String, String>> getAllCurrenciesNames() {
        return ResponseEntity.ok(currencyServiceImpl.getFullCurrencyNames());
    }

}
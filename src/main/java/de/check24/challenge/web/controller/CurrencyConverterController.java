package de.check24.challenge.web.controller;

import de.check24.challenge.request.ConvertRequest;
import de.check24.challenge.service.CurrencyConverter;
import de.check24.challenge.service.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CurrencyConverterController {

    @Autowired
    CurrencyConverter currencyConverter;

    @Autowired
    CurrencyServiceImpl currencyServiceImpl;

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
package com.currencyapi.example.web.controller;

import com.currencyapi.example.request.ConvertRequest;
import com.currencyapi.example.service.CurrencyConverter;
import com.currencyapi.example.service.CurrencyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
public class CurrencyConverterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConverterController.class);
    private final CurrencyConverter currencyConverter;
    private final CurrencyServiceImpl currencyServiceImpl;

    public CurrencyConverterController(CurrencyConverter currencyConverter, CurrencyServiceImpl currencyServiceImpl) {
        this.currencyConverter = currencyConverter;
        this.currencyServiceImpl = currencyServiceImpl;
    }

    @PostMapping("/convert")
    @Operation(summary = "Converts a given amount to the specified currency",
            description = "Provides real-time currency conversion based on the supplied amount and target currency.")
    @ApiResponse(responseCode = "200", description = "Successful conversion",
            content = @Content(schema = @Schema(implementation = Double.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input provided")
    public Mono<ResponseEntity<?>> convertEurAmount(@RequestBody ConvertRequest request) {
        LOGGER.info("Calling converting endpoint POST convert");
        try {
            double amount = Double.parseDouble(request.getAmountToConvert().toString());
            return currencyConverter.convert(amount, request.getTargetCurrency())
                    .map(ResponseEntity::ok);
        } catch (NumberFormatException | NullPointerException e) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be a number"));
        }
    }

    @GetMapping("/currencies")
    @Operation(summary = "Fetches all available currency names",
            description = "Retrieves a map of currency codes to their full names.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of currency names",
            content = @Content(schema = @Schema(implementation = Map.class)))
    public Mono<Map<String, String>> getAllCurrenciesNames() {
        LOGGER.info("Calling currencies endpoint GET currencies");
        return currencyServiceImpl.getFullCurrencyNames();
    }
}

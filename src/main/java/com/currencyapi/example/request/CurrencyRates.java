package com.currencyapi.example.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyRates {
    @JsonProperty("rates")
    private Map<String, Double> rates;

    // You can add other fields if needed, such as "status" and "base"
    @JsonProperty("status")
    private boolean status;

    @JsonProperty("base")
    private String base;

}



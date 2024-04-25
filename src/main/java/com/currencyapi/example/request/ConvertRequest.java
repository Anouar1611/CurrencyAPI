package com.currencyapi.example.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConvertRequest {
    private Double amountToConvert;
    private String targetCurrency;
}

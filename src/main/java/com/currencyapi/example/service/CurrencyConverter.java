package com.currencyapi.example.service;

public interface CurrencyConverter {
    double convert(double amount, String targetCurrency);
}

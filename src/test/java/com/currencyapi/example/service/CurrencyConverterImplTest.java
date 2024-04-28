package com.currencyapi.example.service;

import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CurrencyConverterImplTest {

    public static MockWebServer mockBackEnd;
    public CurrencyConverterImpl currencyConverterImpl;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        currencyConverterImpl = new CurrencyConverterImpl();
        currencyConverterImpl.currencies = mockBackEnd.url("http://localhost:9090/currencies").toString();
    }

    @Test
    void convert_ShouldReturnCorrectConvertedAmount() {
        String responseBody = "{\"status\": \"true\", \"base\": \"EUR\", \"rates\": {\"USD\": 1.114293}}";
        mockBackEnd.enqueue(new MockResponse().newBuilder().body(responseBody).build());
        Mono<Double> result = currencyConverterImpl.convert(100.0, "USD");
        assertEquals(111.4293, result.block());
    }

}



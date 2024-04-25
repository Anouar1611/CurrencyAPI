package com.currencyapi.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurrencyConverterImplTest {

        public static final String CURRENCIES_URL = "http://localhost:9090/currencies";

        @Mock
        private RestTemplate restTemplate;

        private CurrencyConverterImpl currencyConverter;


        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            currencyConverter = new CurrencyConverterImpl();
            currencyConverter.restTemplate = restTemplate;
            currencyConverter.currencies = CURRENCIES_URL;
        }

        @Test
        void testConvertSuccess() {
            String responseBody = "{\"rates\":{\"CHF\":1.038259}}";
            when(restTemplate.getForObject(CURRENCIES_URL, String.class)).thenReturn(responseBody);

            double amount = 22.0;
            double expectedConversionRate = 1.038259;
            double expectedConvertedAmount = amount * expectedConversionRate;
            double actualConvertedAmount = currencyConverter.convert(amount, "CHF");

            verify(restTemplate, times(1)).getForObject(CURRENCIES_URL, String.class);

            assertEquals(expectedConvertedAmount, actualConvertedAmount);
        }

        @Test
        void testConvertInvalidTargetCurrency() {
            String responseBody = "{\"rates\":{\"EUR\":1.0,\"USD\":1.21,\"GBP\":0.89}}";
            when(restTemplate.getForObject(CURRENCIES_URL, String.class)).thenReturn(responseBody);

            double amount = 10.0;
            String invalidTargetCurrency = "XXX";
            double actualConvertedAmount = currencyConverter.convert(amount, invalidTargetCurrency);

            verify(restTemplate, times(1)).getForObject(CURRENCIES_URL, String.class);

            assertEquals(0.0, actualConvertedAmount);
        }

}



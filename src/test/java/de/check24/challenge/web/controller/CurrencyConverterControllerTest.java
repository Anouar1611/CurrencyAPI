package de.check24.challenge.web.controller;

import de.check24.challenge.request.ConvertRequest;
import de.check24.challenge.service.CurrencyConverter;
import de.check24.challenge.service.CurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CurrencyConverterControllerTest {

    @Mock
    private CurrencyConverter currencyConverter;

    @Mock
    private CurrencyServiceImpl currencyServiceImpl;

    private CurrencyConverterController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CurrencyConverterController();
        controller.currencyConverter = currencyConverter;
        controller.currencyServiceImpl = currencyServiceImpl;
    }

    @Test
    void testConvertEurAmount() {
        ConvertRequest request = new ConvertRequest(100.0, "USD");
        double expectedConvertedAmount = 121.0;
        when(currencyConverter.convert(100.0, "USD")).thenReturn(expectedConvertedAmount);

        ResponseEntity<Double> response = controller.convertEurAmount(request);

        assertEquals(expectedConvertedAmount, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetAllCurrenciesNames() {
        Map<String, String> currencyNames = new HashMap<>();
        currencyNames.put("USD", "United States Dollar");
        currencyNames.put("EUR", "Euro");
        when(currencyServiceImpl.getFullCurrencyNames()).thenReturn(currencyNames);

        ResponseEntity<Map<String, String>> response = controller.getAllCurrenciesNames();

        assertEquals(currencyNames, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}
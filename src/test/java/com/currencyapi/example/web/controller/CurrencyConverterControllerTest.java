package com.currencyapi.example.web.controller;

import com.currencyapi.example.service.CurrencyConverter;
import com.currencyapi.example.service.CurrencyServiceImpl;
import com.currencyapi.example.request.ConvertRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = CurrencyConverterController.class)
class CurrencyConverterControllerTest {

    @MockBean
    private CurrencyConverter currencyConverter;

    @MockBean
    private CurrencyServiceImpl currencyServiceImpl;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        when(currencyConverter.convert(100.0, "USD")).thenReturn(Mono.just(111.4293));
    }

    @Test
    void testConvertEurAmount() {
        ConvertRequest request = new ConvertRequest(100.0, "USD");

        webTestClient.post().uri("/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Double.class)
                .isEqualTo(111.4293);
    }

}

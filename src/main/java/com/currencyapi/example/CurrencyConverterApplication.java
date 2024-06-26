package com.currencyapi.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application start point
 */
@SpringBootApplication
public class CurrencyConverterApplication {

    /**
     * Application entrypoint
     *
     * @param args system arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }
}

package de.check24.challenge.request;

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

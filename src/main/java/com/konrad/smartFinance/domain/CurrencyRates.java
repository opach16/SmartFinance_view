package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRates {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("price")
    private BigDecimal price;
}

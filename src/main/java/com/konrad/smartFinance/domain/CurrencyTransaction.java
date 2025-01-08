package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.konrad.smartFinance.CurrencyTransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyTransaction {

    @JsonProperty("currencySymbol")
    private String symbol;
    @JsonProperty("transactionType")
    private CurrencyTransactionType transactionType;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("transactionDate")
    private LocalDate transactionDate;
    @JsonProperty("value")
    private BigDecimal transactionValue;
    @JsonProperty("currentValue")
    private BigDecimal currentValue;
}

package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.konrad.smartFinance.CurrencySymbol;
import com.konrad.smartFinance.CurrencyTransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyTransaction {

    @JsonProperty("id")
    private Long transactionId;
    @JsonProperty("transactionType")
    private CurrencyTransactionType transactionType;
    @JsonProperty("currencySymbol")
    private CurrencySymbol symbol;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("transactionDate")
    private LocalDate transactionDate;
    @JsonProperty("transactionValue")
    private BigDecimal transactionValue;
    @JsonProperty("currentValue")
    private BigDecimal currentValue;
}

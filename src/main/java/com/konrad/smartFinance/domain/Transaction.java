package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.konrad.smartFinance.AccountTransactionType;
import com.konrad.smartFinance.CurrencySymbol;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    @JsonProperty("transactionDate")
    private LocalDate transactionDate;
    @JsonProperty("transactionType")
    private AccountTransactionType transactionType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("currencySymbol")
    private CurrencySymbol symbol;
    @JsonProperty("amount")
    private BigDecimal amount;
}

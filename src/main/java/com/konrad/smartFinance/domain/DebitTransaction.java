package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.konrad.smartFinance.CurrencySymbol;
import com.konrad.smartFinance.DebitTransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebitTransaction {

    @JsonProperty("id")
    private Long transactionId;
    @JsonProperty("transactionDate")
    private LocalDate transactionDate;
    @JsonProperty("transactionType")
    private DebitTransactionType transactionType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("currencySymbol")
    private CurrencySymbol symbol;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("transactionValue")
    private BigDecimal transactionValue;
}

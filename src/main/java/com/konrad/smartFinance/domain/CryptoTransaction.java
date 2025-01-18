package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.konrad.smartFinance.CryptoSymbol;
import com.konrad.smartFinance.CryptoTransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoTransaction {

    @JsonProperty("id")
    private Long transactionId;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("transactionType")
    private CryptoTransactionType transactionType;
    @JsonProperty("cryptocurrencySymbol")
    private CryptoSymbol symbol;
    @JsonProperty("cryptocurrencyName")
    private String name;
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

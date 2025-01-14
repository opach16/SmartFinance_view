package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty("user")
    private User user;
    @JsonProperty("mainCurrency")
    private CurrencyRates mainCurrency;
    @JsonProperty("mainBalance")
    private BigDecimal mainBalance;
    @JsonProperty("assetsBalance")
    private BigDecimal assetsBalance;
    @JsonProperty("totalBalance")
    private BigDecimal totalBalance;
}

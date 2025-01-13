package com.konrad.smartFinance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.konrad.smartFinance.CurrencySymbol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {

    @JsonProperty("user")
    private User user;
    @JsonProperty("mainCurrency")
    private CurrencySymbol mainCurrency;
    @JsonProperty("mainBalance")
    private BigDecimal mainBalance;

    public UserRegistration(User user) {
        this.user = user;
    }
}

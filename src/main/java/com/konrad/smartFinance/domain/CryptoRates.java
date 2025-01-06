package com.konrad.smartFinance.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CryptoRates {

    private String name;
    private String symbol;
    private BigDecimal price;
    private BigDecimal marketCap;
    private BigDecimal ath;
    private int rank;
}

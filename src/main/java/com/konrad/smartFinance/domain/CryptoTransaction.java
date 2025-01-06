package com.konrad.smartFinance.domain;

import com.konrad.smartFinance.CryptoSymbol;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CryptoTransaction {

    private CryptoSymbol symbol;
    private String name;
    private BigDecimal amount;
    private BigDecimal price;
    private LocalDate transactionDate;
    private BigDecimal transactionValue;
    private BigDecimal currentValue;
}

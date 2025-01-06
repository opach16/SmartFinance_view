package com.konrad.smartFinance.domain;

import com.konrad.smartFinance.CurrencySymbol;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CurrencyTransaction {

    private CurrencySymbol symbol;
    private String name;
    private BigDecimal amount;
    private BigDecimal price;
    private LocalDate transactionDate;
    private BigDecimal transactionValue;
    private BigDecimal currentValue;
}

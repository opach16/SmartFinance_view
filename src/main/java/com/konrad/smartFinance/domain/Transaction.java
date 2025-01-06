package com.konrad.smartFinance.domain;

import com.konrad.smartFinance.AccountTransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Transaction {

    private LocalDate date;
    private AccountTransactionType type;
    private String name;
    private BigDecimal amount;
}

package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CurrencyTransaction;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Getter
public class CurrencyTransactionService {

    private final Set<CurrencyTransaction> currencyTransactions;
    private static CurrencyTransactionService currencyTransactionService;
    private final SmartFinanceClient smartFinanceClient = new SmartFinanceClient(new RestTemplate());

    private CurrencyTransactionService() {
        currencyTransactions = addCurrencyTransactions();
    }

    public static CurrencyTransactionService getInstance() {
        if (currencyTransactionService == null) {
            currencyTransactionService = new CurrencyTransactionService();
        }
        return currencyTransactionService;
    }

    public Set<CurrencyTransaction> getCurrencyTransactions() {
        return new HashSet<>(currencyTransactions);
    }

    private Set<CurrencyTransaction> addCurrencyTransactions() {
        return smartFinanceClient.fetchCurrencyTransactions();
    }
}

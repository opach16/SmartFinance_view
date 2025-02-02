package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CurrencyTransaction;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Service
public class CurrencyTransactionService {

    private final SmartFinanceClient smartFinanceClient;
    private static CurrencyTransactionService currencyTransactionService;
    private Set<CurrencyTransaction> currencyTransactions;

    private CurrencyTransactionService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        currencyTransactions = new HashSet<>();
    }

    public static CurrencyTransactionService getInstance() {
        if (currencyTransactionService == null) {
            currencyTransactionService = new CurrencyTransactionService();
        }
        return currencyTransactionService;
    }

    public void updateCurrencyTransactions() {
        currencyTransactions = smartFinanceClient.fetchCurrencyTransactions();
    }

    public Set<CurrencyTransaction> findBySymbol(String symbol) {
        return currencyTransactions.stream()
                .filter(transaction -> transaction.getSymbol().toString().toLowerCase().contains(symbol.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public void save(CurrencyTransaction transaction) {
        if (transaction.getTransactionId() == null) {
            smartFinanceClient.addCurrencyTransaction(transaction);
        } else {
            smartFinanceClient.updateCurrencyTransaction(transaction);
        }
    }

    public void delete(CurrencyTransaction transaction) {
        if (transaction.getTransactionId() != null) {
            smartFinanceClient.deleteCurrencyTransaction(transaction);
        }
    }
}

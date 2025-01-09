package com.konrad.smartFinance.service;

import com.konrad.smartFinance.client.SmartFinanceClient;
import com.konrad.smartFinance.domain.CurrencyTransaction;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Getter
@Service
public class CurrencyTransactionService {

    private final SmartFinanceClient smartFinanceClient;
    private static CurrencyTransactionService currencyTransactionService;
    private Set<CurrencyTransaction> currencyTransactions;

    private CurrencyTransactionService() {
        smartFinanceClient = new SmartFinanceClient(new RestTemplate());
        updateCurrencyTransactions();
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

    public void save(CurrencyTransaction transaction) {
        this.currencyTransactions.add(transaction);
    }

    public void delete(CurrencyTransaction transaction) {
        this.currencyTransactions.remove(transaction);
    }
}
